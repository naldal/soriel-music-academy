package com.soriel.music.springboot.domain.soriel;

import com.soriel.music.springboot.domain.BaseTimeEntity;
import com.soriel.music.springboot.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Entity(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IntegrationEntity extends BaseTimeEntity {
    //가제

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true, length = 200)
    private String upwd;

    @Column(nullable = true, length = 200)
    private String youtubeLink;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Builder
    public IntegrationEntity(Long id, String email, String name, String upwd, String youtubeLink, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.upwd = upwd;
        this.youtubeLink = youtubeLink;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public IntegrationEntity update(String name, String email) {
        this.name = name;
        this.email = email;

        return this;
    }

    public static IntegrationEntity createIntegration(){
        return new IntegrationEntity();
    }
}
