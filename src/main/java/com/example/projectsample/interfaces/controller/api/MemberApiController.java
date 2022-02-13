package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.service.MemberService;
import com.example.projectsample.common.util.aop.ResponseJsonResult;
import com.example.projectsample.common.util.exception.BusinessException;
import com.example.projectsample.interfaces.dto.MemberJoinRequestDto;
import com.example.projectsample.interfaces.dto.MemberLoginRequestDto;
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
    @ResponseJsonResult
    public Object join(@RequestBody MemberJoinRequestDto member) {
        return memberService.insertMember(member);
    }

    /**
     * 로그인 메서드
     * @param httpSession 세션
     * @param memberLoginRequestDto 입력한 아이디와 비밀번호
     * @return memberDto
     */
    @PostMapping("/login")
    @ResponseJsonResult
    public Object userLogin(HttpSession httpSession, @RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        return memberService.memberLogin(memberLoginRequestDto.getCustomMemberId(), memberLoginRequestDto.getPassword(), httpSession);
    }

    /**
     * ID 중복체크 메서드
     * @param memberId 유저 아이디
     * @return true는 중복, false는 중복되지 않은 ID
     */
    @GetMapping("/exist")
    @ResponseJsonResult
    public Object isDuplicated(@RequestParam String memberId, @RequestParam String email) {
        return memberService.isDuplicated(memberId, email);
    }

    @GetMapping("/logout")
    @ResponseJsonResult
    public Object memberLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "";
    }

    @GetMapping("/login-check")
    @ResponseJsonResult
    public Object memberLoginCheck(HttpSession httpSession) {
        Object data = httpSession.getAttribute("MemberInfo");
        if (data == null) {
            throw new BusinessException("로그인이 필요합니다");
        }
        return "";
    }
}
