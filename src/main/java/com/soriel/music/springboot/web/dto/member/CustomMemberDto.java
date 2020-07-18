package com.soriel.music.springboot.web.dto.member;

import com.soriel.music.springboot.domain.soriel.MemberEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomMemberDto extends User {

    private String customName;

    public CustomMemberDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomMemberDto(MemberEntity memberEntity, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.customName = memberEntity.getName();
    }

}
