package com.example.projectsample.application.service;

import com.example.projectsample.application.model.dto.MemberResponseDto;
import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.interfaces.dto.MemberJoinRequestDto;
import com.example.projectsample.interfaces.dto.MemberLoginRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void 회원가입테스트() {
        MemberJoinRequestDto req = new MemberJoinRequestDto();
        req.setCustomMemberId("mockId");
        req.setPassword("mockPassword");
        req.setEmail("mock@mock.com");
        req.setName("mockName");
        Member member = memberService.insertMember(req);
        assertThat(member.getName()).isEqualTo("mockName");
    }

    @Test
    void 중복체크테스트() {
        boolean isDuplicated = memberService.isDuplicated("admin", "admin");
        assertThat(isDuplicated).isTrue();
    }
}