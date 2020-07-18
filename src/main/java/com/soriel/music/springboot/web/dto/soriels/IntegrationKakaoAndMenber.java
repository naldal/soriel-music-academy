package com.soriel.music.springboot.web.dto.soriels;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;

public interface IntegrationKakaoAndMenber extends OAuth2User, Serializable, UserDetails, CredentialsContainer {

}
