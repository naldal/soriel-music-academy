package com.soriel.music.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN", "어드민"),
    MEMBER("ROLE_MEMBER", "일반 사용자");

    private String key;
    private String title;
}
