package com.soriel.music.springboot.web.dto.soriels;

import com.soriel.music.springboot.domain.soriel.KakaoEntity;
import com.soriel.music.springboot.domain.soriel.MemberEntity;
import com.soriel.music.springboot.service.soriel.CustomOAuth2UserService;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomDefaultOAuth2User extends DefaultOAuth2User {

    //principal 객체가 되시는 클래스
    private String customName;

    public CustomDefaultOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        super(authorities, attributes, nameAttributeKey);
    }

    public CustomDefaultOAuth2User(KakaoEntity entity, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        super(authorities, attributes, nameAttributeKey);
        this.customName = entity.getName();
    }
}
