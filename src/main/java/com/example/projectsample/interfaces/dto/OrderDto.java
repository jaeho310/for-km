package com.example.projectsample.interfaces.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderDto {

    private Long id;

    @NonNull
    private int count;

    @NonNull
    private String productName;
}
