package com.example.projectsample.application.model.dto;

import com.example.projectsample.application.model.entity.Order;
import com.example.projectsample.application.model.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderResponseDto {

    private int count;

    private Product product;

    private String createdAt;
}
