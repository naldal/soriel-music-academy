package com.soriel.music.springboot.web.dto.soriels;

import com.soriel.music.springboot.domain.Role;
import com.soriel.music.springboot.domain.soriel.IntegrationEntity;
import com.soriel.music.springboot.domain.soriel.KakaoEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String registrationId;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String registrationId) {
        this.attributes = attributes;
        this.registrationId = registrationId;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofKakao(registrationId, "id", attributes);
    }

    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        profile.put("email", kakaoAccount.get("email"));
        profile.put("username", profile.get("nickname"));

        profile.put("id", attributes.get("id"));


        return OAuthAttributes.builder().name((String) profile.get("username"))
                                        .email((String) profile.get("email"))
                                        .attributes(profile)
                                        .nameAttributeKey(userNameAttributeName)
                                        .registrationId(registrationId)
                                        .build();
    }

    public IntegrationEntity toEntity() {
        return IntegrationEntity.builder()
                .name(name)
                .email(email)
                .role(Role.MEMBER)
                .build();
    }

}
