package com.example.projectsample.interfaces.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginDto {

    @NonNull
    String memberId;

    @NonNull
    String password;
}
