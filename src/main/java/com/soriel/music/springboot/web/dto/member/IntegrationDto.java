package com.soriel.music.springboot.web.dto.member;

import com.soriel.music.springboot.domain.Role;
import com.soriel.music.springboot.domain.soriel.IntegrationEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class IntegrationDto {
    //가제

    private Long id;
    private String name;
    private String email;
    private String upwd;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public IntegrationEntity toEntity() {
        return IntegrationEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .upwd(upwd)
                .role(Role.MEMBER)
                .build();
    }

    public IntegrationEntity toAdminEntity() {
        return IntegrationEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .upwd(upwd)
                .role(Role.ADMIN)
                .build();
    }


    @Builder
    public IntegrationDto(Long id, String name, String email, String upwd, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.upwd = upwd;
        this.role = role;
    }
}
