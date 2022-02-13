package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.service.MemberService;
import com.example.projectsample.interfaces.dto.MemberJoinRequestDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    Gson gson;

    @BeforeEach
    void setup() {
        this.gson = new Gson();
    }

    @Test
    @DisplayName("회원가입 테스트")
    void 회원가입테스트() throws Exception {
        MemberJoinRequestDto memberJoinRequestDto = MemberJoinRequestDto.builder()
                .customMemberId("kmong123")
                .name("user1")
                .password("abc")
                .build();

        Member user1 = Member.builder().name("user1").build();

        given(memberService.insertMember(any())).willReturn(user1);

        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(gson.toJson(memberJoinRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("user1")));
    }

    @Test
    @DisplayName("중복확인 테스트")
    void 중복확인테스트() throws Exception {
        String memberId = "abc";
        String email = "a@gmail.com";
        given(memberService.isDuplicated(memberId, email)).willReturn(false);
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("memberId", memberId);
        queryParams.add("email", email);

        mvc.perform(get("/api/members/exist")
                        .params(queryParams))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
    }
}