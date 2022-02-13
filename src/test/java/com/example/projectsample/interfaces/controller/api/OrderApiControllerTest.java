package com.example.projectsample.interfaces.controller.api;
import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.interfaces.dto.OrderRequestDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderApiControllerTest {

    @Autowired
    MockMvc mvc;

    Gson gson;

    @BeforeEach
    void setup() {
        this.gson = new Gson();
    }

    @Test
    void createOrder() throws Exception {
        OrderRequestDto orderDto = OrderRequestDto.builder().productName("사과").count(3).build();
        String content = gson.toJson(orderDto);
        mvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(content)
                        .sessionAttr("memberInfo", Member.builder().id(1L).build()
                        ))
                .andExpect(status().isOk());
    }
}