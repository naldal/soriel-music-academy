package com.soriel.music.springboot.web.dto.member;

import com.soriel.music.springboot.domain.soriel.IntegrationEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.*;

@Getter
public class CustomIntegrationDto implements IntegrationKakaoAndMenber{

    private Long id;
    private String customName;
    private String upwd;
    private DefaultOAuth2User defaultOAuth2User;
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private final Set<GrantedAuthority> authorities;

    public CustomIntegrationDto(IntegrationEntity integrationEntity, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        new User(username, password, authorities);
        this.customName = integrationEntity.getName();
        this.upwd = integrationEntity.getUpwd();
        this.id = integrationEntity.getId();
        this.authorities = Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities(authorities)));
    }

    public CustomIntegrationDto(IntegrationEntity integrationEntity, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        this.defaultOAuth2User = new DefaultOAuth2User(authorities, attributes, nameAttributeKey);
        this.customName = integrationEntity.getName();
        this.id = integrationEntity.getId();
        this.authorities = Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities(authorities)));
        this.attributes = Collections.unmodifiableMap(new LinkedHashMap<>(attributes));
        this.nameAttributeKey = nameAttributeKey;
    }

    private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(Comparator.comparing(GrantedAuthority::getAuthority));
        sortedAuthorities.addAll(authorities);
        return sortedAuthorities;
    }

    @Override
    public void eraseCredentials() {
        this.upwd = null;
    }

    @Override
    public String getPassword() {
        return this.upwd;
    }

    @Override
    public String getUsername() {
        return customName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }



}
