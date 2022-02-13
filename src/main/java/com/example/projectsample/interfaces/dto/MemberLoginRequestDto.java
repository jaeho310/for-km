package com.example.projectsample.interfaces.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginRequestDto {

    @NonNull
    String customMemberId;

    @NonNull
    String password;

    @NonNull
    String email;
}
