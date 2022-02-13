package com.example.projectsample.application.service;

import com.example.projectsample.application.model.dto.OrderResponseDto;
import com.example.projectsample.application.model.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    @Transactional
    @DisplayName("주문, 주문내역 조회")
    void 주문후_주문내역조회() {
        int count = 1;
        Long memberId = 1L;
        String productName = "사과";
        OrderResponseDto order = orderRequest(memberId, count, productName);
        List<OrderResponseDto> orders = orderListRequest(memberId);
        Boolean isContain = false;
        for (OrderResponseDto o : orders) {
            if (Objects.equals(o.getProduct().getName(), order.getProduct().getName())) {
                isContain = true;
                break;
            }
        }
        assertThat(isContain).isEqualTo(true);
    }

    private List<OrderResponseDto> orderListRequest(Long memberId) {
        return orderService.getOrderListByMemberId(memberId);
    }

    private OrderResponseDto orderRequest(Long memberId, int count, String productName) {
        return orderService.insertOrder(memberId, count, productName);
    }

}