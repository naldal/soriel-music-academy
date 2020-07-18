package com.soriel.music.springboot.web.dto.member;

import com.soriel.music.springboot.domain.soriel.KakaoEntity;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(KakaoEntity memberEntity) {
        this.name = memberEntity.getName();
        this.email = memberEntity.getEmail();
    }
}
