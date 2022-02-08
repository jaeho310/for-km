package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.service.MemberService;
import com.example.projectsample.interfaces.dto.MemberDto;
import com.example.projectsample.interfaces.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입 메서드
     * @param member 사용자정보
     * @return 사용자정보
     */
    @PostMapping("/join")
    public Object join(@RequestBody MemberDto member) {
        return memberService.insertMember(member);
    }

    /**
     * 로그인 메서드
     * @param httpSession 세션
     * @param memberLoginDto 입력한 아이디와 비밀번호
     * @return memberDto
     */
    @PostMapping("/login")
    public Object userLogin(HttpSession httpSession, @RequestBody MemberLoginDto memberLoginDto) {
        return memberService.memberLogin(memberLoginDto.getMemberId(), memberLoginDto.getPassword(), httpSession);
    }

    /**
     * ID 중복체크 메서드
     * @param memberId 유저 아이디
     * @return true는 중복, false는 중복되지 않은 ID
     */
    @GetMapping("/{memberId}/exist")
    public Object isDuplicated(@PathVariable String memberId) {
        return memberService.isDuplicated(memberId);
    }


}
