package com.example.projectsample.interfaces.controller.api;
import com.example.projectsample.application.model.dto.OrderResponseDto;
import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.model.entity.Product;
import com.example.projectsample.application.service.OrderService;
import com.example.projectsample.interfaces.dto.OrderRequestDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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

@WebMvcTest(OrderApiController.class)
class OrderApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    OrderService orderService;

    Gson gson;

    @BeforeEach
    void setup() {
        this.gson = new Gson();
    }

    @Test
    void createOrder() throws Exception {
        OrderRequestDto orderReqDto = OrderRequestDto.builder().productName("사과").count(3).build();
        OrderResponseDto orderResDto = OrderResponseDto.builder()
                .count(3)
                .product(new Product())
                .build();
        given(orderService.insertOrder(1L, orderReqDto.getCount(), orderReqDto.getProductName())).willReturn(orderResDto);

        String content = gson.toJson(orderReqDto);
        mvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(content)
                        .sessionAttr("MemberInfo", Member.builder().id(1L).build()
                        ))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("3")));
    }

    @Test
    void getOrderList() throws Exception {
        OrderResponseDto orderResDto = OrderResponseDto.builder()
                .count(3)
                .product(new Product())
                .build();
        List<OrderResponseDto> response = new ArrayList<>();
        response.add(orderResDto);

        given(orderService.getOrderListByMemberId(1L)).willReturn(response);
        mvc.perform(get("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .sessionAttr("MemberInfo", Member.builder().id(1L).build()
                        ))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("3")));
    }
}