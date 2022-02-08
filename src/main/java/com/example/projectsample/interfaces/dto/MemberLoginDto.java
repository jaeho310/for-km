package com.example.projectsample.interfaces.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class MemberLoginDto {

    @NonNull
    String memberId;

    @NonNull
    String password;
}
