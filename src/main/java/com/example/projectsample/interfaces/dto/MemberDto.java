package com.example.projectsample.interfaces.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {

    private Long id;

    @NonNull
    private String memberId;

    @NonNull
    private String password;

    @NonNull
    private String name;

//    private Role role;
}
