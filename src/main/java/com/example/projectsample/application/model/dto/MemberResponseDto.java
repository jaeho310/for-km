package com.example.projectsample.application.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberResponseDto {
    private String customMemberId;

    private String name;

    private String email;
}
