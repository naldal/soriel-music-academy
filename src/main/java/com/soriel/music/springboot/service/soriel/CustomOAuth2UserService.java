package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.soriel.KakaoEntity;
import com.soriel.music.springboot.domain.soriel.KakaoRepository;
import com.soriel.music.springboot.domain.soriel.MemberEntity;
import com.soriel.music.springboot.domain.soriel.MemberRepository;
import com.soriel.music.springboot.web.dto.soriels.CustomDefaultOAuth2User;
import com.soriel.music.springboot.web.dto.soriels.KakaoDto;
import com.soriel.music.springboot.web.dto.soriels.OAuthAttributes;
import com.soriel.music.springboot.web.dto.soriels.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final KakaoRepository kakaoRepository;
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

        KakaoEntity kakaoEntity = saveOrUpdate(attributes);

        System.out.println("tostring : "+kakaoEntity.toString());
        System.out.println("getname : "+kakaoEntity.getName());


        //System.out.println("111 : "+attributes.getName());

        return new CustomDefaultOAuth2User(kakaoEntity
                                            ,Collections.singleton(new SimpleGrantedAuthority(kakaoEntity.getRoleKey()))
                                            ,attributes.getAttributes()
                                            ,attributes.getNameAttributeKey()
                                            );
    }

    private KakaoEntity saveOrUpdate(OAuthAttributes attributes) {
        KakaoEntity kakaoEntity = kakaoRepository.findByName(attributes.getName())
                .map(entity->entity.update(attributes.getName(), attributes.getEmail()))
                .orElse(attributes.toEntity());

        return kakaoRepository.save(kakaoEntity);
    }
}
