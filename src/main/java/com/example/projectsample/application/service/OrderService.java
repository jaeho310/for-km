package com.example.projectsample.application.service;

import com.example.projectsample.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Object getOrderList(String memberId) {
        return null;
    }
}
