package com.example.projectsample.application.service;

import com.example.projectsample.application.model.dto.MemberResponseDto;
import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.repository.MemberRepository;
import com.example.projectsample.common.util.exception.BusinessException;
import com.example.projectsample.interfaces.dto.MemberJoinRequestDto;
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
     * @param memberJoinRequestDto 사용자정보를 담는 dto
     * @return Member
     */
    public Member insertMember(MemberJoinRequestDto memberJoinRequestDto) {
        memberJoinRequestDto.setPassword(passwordEncoder.encode(memberJoinRequestDto.getPassword()));
        Member member = modelMapper.map(memberJoinRequestDto, Member.class);
        return memberRepository.save(member);
    }

    public boolean isDuplicated(String memberId, String email) {
        Optional<Member> memberByMemberId = memberRepository.findByCustomMemberId(memberId);
        if (memberByMemberId.isPresent()) {
            return true;
        }
        Optional<Member> memberByEmail = memberRepository.findByEmail(email);
        return memberByEmail.isPresent();
    }

    /**
     * db에 복호화된 password와 유저가 입력한 password를 복호화한 값이 같은지 비교
     * 로그인 성공시 20분동안 세션 부여
     * @param memberId 사용자가 입력한 아이디
     * @param password 사용자가 입력한 비밀번호
     */
    @Transactional
    public MemberResponseDto memberLogin(String memberId, String password, HttpSession httpSession) {
        Member member = memberRepository.findByCustomMemberId(memberId)
                .orElseThrow(() -> new BusinessException("등록되지 않은 사용자입니다"));
        if (passwordEncoder.matches(password, member.getPassword())) {
            httpSession.setAttribute("MemberInfo", member);
            httpSession.setMaxInactiveInterval(20*60);
            return MemberResponseDto.builder()
                    .name(member.getName())
                    .customMemberId(member.getCustomMemberId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();
        } else {
            throw new BusinessException("비밀번호가 일치하지 않습니다.");
        }
    }
}
