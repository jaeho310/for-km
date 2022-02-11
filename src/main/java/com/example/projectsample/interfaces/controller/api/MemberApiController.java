package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.service.MemberService;
import com.example.projectsample.common.util.aop.ResponseJsonResult;
import com.example.projectsample.common.util.exception.BusinessException;
import com.example.projectsample.interfaces.dto.MemberDto;
import com.example.projectsample.interfaces.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;

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
    @ResponseJsonResult
    public Object userLogin(HttpSession httpSession, @RequestBody MemberLoginDto memberLoginDto) {
        return memberService.memberLogin(memberLoginDto.getCustomMemberId(), memberLoginDto.getPassword(), httpSession);
    }

    /**
     * ID 중복체크 메서드
     * @param memberId 유저 아이디
     * @return true는 중복, false는 중복되지 않은 ID
     */
    @GetMapping("/{memberId}/exist")
    @ResponseJsonResult
    public Object isDuplicated(@PathVariable String memberId) {
        return memberService.isDuplicated(memberId);
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
//        Object data = httpSession.getAttribute("MemberInfo");
//        if (data == null) {
//            throw new BusinessException("로그인이 필요합니다");
//        }
        return "";
    }
}
