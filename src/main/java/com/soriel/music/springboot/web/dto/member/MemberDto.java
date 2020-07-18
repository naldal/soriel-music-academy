package com.soriel.music.springboot.web.dto.member;

import com.soriel.music.springboot.domain.Role;
import com.soriel.music.springboot.domain.soriel.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private String upwd;
    private String youtubeLink;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .upwd(upwd)
                .youtubeLink(youtubeLink)
                .role(Role.MEMBER)
                .build();
    }

    @Builder
    public MemberDto(Long id, String name, String email, String upwd, String youtubeLink, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.upwd = upwd;
        this.youtubeLink = youtubeLink;
        this.role = role;
    }
}
