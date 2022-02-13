package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.service.OrderService;
import com.example.projectsample.common.util.aop.ResponseJsonResult;
import com.example.projectsample.interfaces.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("")
    @ResponseJsonResult
    public Object getOrderList(HttpSession httpSession) {
        Member memberInfo = (Member) httpSession.getAttribute("MemberInfo");
        return orderService.getOrderListByMemberId(memberInfo.getId());
    }

    @PostMapping("")
    @ResponseJsonResult
    public Object newOrder(HttpSession httpSession, @RequestBody OrderRequestDto orderDto) {
        Member memberInfo = (Member) httpSession.getAttribute("MemberInfo");
        return orderService.insertOrder(memberInfo.getId(), orderDto.getCount(), orderDto.getProductName());
    }
}
