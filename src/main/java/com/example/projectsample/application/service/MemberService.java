package com.example.projectsample.application.service;

import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.repository.MemberRepository;
import com.example.projectsample.common.util.exception.BusinessException;
import com.example.projectsample.interfaces.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;
    /**
     * 회원가입 메서드
     * @param memberDto 사용자정보를 담는 dto
     * @return
     */
    public Member insertMember(MemberDto memberDto) {
        if (isDuplicated(memberDto.getCustomMemberId())) {
            throw new BusinessException("중복체크가 되지 않은 요청입니다.");
        }
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = modelMapper.map(memberDto, Member.class);
        return memberRepository.save(member);
    }

    public boolean isDuplicated(String memberId) {
        Optional<Member> member = memberRepository.findByCustomMemberId(memberId);
        return member.isPresent();
    }

    /**
     * db에 복호화된 password와 유저가 입력한 password를 복호화한 값이 같은지 비교
     * 로그인 성공시 20분의 유지시간을 가진 세션 부여
     * @param memberId 사용자가 입력한 아이디
     * @param password 사용자가 입력한 비밀번호
     */
    @Transactional
    public Object memberLogin(String memberId, String password, HttpSession httpSession) {
        Member member = memberRepository.findByCustomMemberId(memberId)
                .orElseThrow(() -> new BusinessException("등록되지 않은 사용자입니다"));
        if (passwordEncoder.matches(password, member.getPassword())) {
            httpSession.setAttribute("MemberInfo", member);
            httpSession.setMaxInactiveInterval(20*60);
            return member;
        } else {
            throw new BusinessException("비밀번호가 일치하지 않습니다.");
        }
    }
}
