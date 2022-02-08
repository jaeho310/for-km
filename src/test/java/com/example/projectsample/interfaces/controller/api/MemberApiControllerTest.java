package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.service.MemberService;
import com.example.projectsample.interfaces.dto.MemberDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
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
    void 로그인테스트() throws Exception {
        MemberDto memberDto = MemberDto.builder()
                .memberId("kmong123")
                .password("abc")
                .build();

        given(memberService.insertMember(memberDto)).willReturn("success");

        mvc.perform(post("/api/members/join")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(gson.toJson(memberDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
    }

    @Test
    @DisplayName("중복확인 테스트")
    void 중복확인테스트() throws Exception {
        String memberId = "abc";
        given(memberService.isDuplicated(memberId)).willReturn(false);
        String format = String.format("/api/members/%s/exist", memberId);
        mvc.perform(get(format))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
    }
}