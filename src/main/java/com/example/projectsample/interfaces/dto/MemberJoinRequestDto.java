package com.example.projectsample.interfaces.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberJoinRequestDto {

    private Long id;

    @NonNull
    private String customMemberId;

    @NonNull
    private String password;

    @NonNull
    private String name;

//    private Role role;
}
