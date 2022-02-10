package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.service.OrderService;
import com.example.projectsample.common.util.aop.ResponseJsonResult;
import com.example.projectsample.interfaces.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/")
    @ResponseJsonResult
    public Object getOrderList(HttpSession httpSession) {
        Member memberInfo = (Member) httpSession.getAttribute("memberInfo");
        return orderService.getOrderList(memberInfo.getMemberId());
    }
}
