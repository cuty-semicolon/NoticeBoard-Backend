package com.example.board.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role { // 사용자 권한 을 위한 Role
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반사용자");

    private final String key;
    private final String title;
}

// 사용자 정보 담당 도메인 BasTimeEntity
// 사용자괼년 CRUD 작업할 UserRepository