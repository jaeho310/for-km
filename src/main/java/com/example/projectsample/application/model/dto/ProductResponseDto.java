package com.example.projectsample.application.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductResponseDto {

    private String name;

    private int price;
}
