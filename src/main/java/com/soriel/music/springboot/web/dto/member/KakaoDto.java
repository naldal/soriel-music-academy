package com.soriel.music.springboot.web.dto.member;

import com.soriel.music.springboot.domain.Role;
import com.soriel.music.springboot.domain.soriel.KakaoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class KakaoDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public KakaoEntity toEntity() {
        return KakaoEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .role(Role.MEMBER)
                .build();
    }

    @Builder
    public KakaoDto(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
