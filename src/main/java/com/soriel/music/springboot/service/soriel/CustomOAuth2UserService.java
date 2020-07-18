package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.soriel.*;
import com.soriel.music.springboot.web.dto.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final KakaoRepository kakaoRepository;
    private final IntegrationRepository integrationRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //현재 진행중인 서비스를 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //로그인 진행시 키가되는 pk값을 의미
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        IntegrationEntity kakaointegrationEntity = saveOrUpdate(attributes);

        return new CustomIntegrationDto(kakaointegrationEntity
                                            ,Collections.singleton(new SimpleGrantedAuthority(kakaointegrationEntity.getRoleKey()))
                                            ,attributes.getAttributes()
                                            ,attributes.getNameAttributeKey()
                                            );
    }

    private IntegrationEntity saveOrUpdate(OAuthAttributes attributes) {
        IntegrationEntity kakaoEntity = integrationRepository.findByName(attributes.getName())
                .map(entity->entity.update(attributes.getName(), attributes.getEmail()))
                .orElse(attributes.toEntity());

        return integrationRepository.save(kakaoEntity);
    }
}
