package com.example.projectsample.application.service;

import com.example.projectsample.application.model.dto.OrderResponseDto;
import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.model.entity.Order;
import com.example.projectsample.application.model.entity.Product;
import com.example.projectsample.application.repository.MemberRepository;
import com.example.projectsample.application.repository.OrderRepository;
import com.example.projectsample.application.repository.ProductRepository;
import com.example.projectsample.common.util.exception.BusinessException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;


    public List<OrderResponseDto> getOrderListByMemberId(Long memberId) {
        List<Order> orders = orderRepository.findAllByMemberId(memberId);
        List<OrderResponseDto> response = new ArrayList<>();
        for (Order order : orders) {
            response.add(OrderResponseDto.builder()
                    .count(order.getCount())
                    .product(order.getProduct())
                    .createdAt(order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build());
        }
        return response;
    }

    @Transactional
    public OrderResponseDto insertOrder(Long memberId, int count, String productName) {
        Product product = productRepository.findByName(productName).orElseThrow(
                () -> {
                    throw new BusinessException("상품정보가 존재하지 않습니다");
                });
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> {
                    throw new BusinessException("사용자 정보가 존재하지 않습니다");
                });
        Order order = Order.builder().product(product).count(count).member(member).build();
        Order o = orderRepository.save(order);
        return OrderResponseDto.builder()
                .count(o.getCount())
                .product(o.getProduct())
                .build();
    }
}
