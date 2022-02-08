package com.example.projectsample.interfaces.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("관리자"),
    USER("사용자");

    private final String userRole;
}
