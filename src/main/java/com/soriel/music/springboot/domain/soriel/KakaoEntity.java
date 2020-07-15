package com.soriel.music.springboot.domain.soriel;

import com.soriel.music.springboot.domain.BaseTimeEntity;
import com.soriel.music.springboot.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@ToString
public class KakaoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true, length = 200)
    private String youtubeLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Builder
    public KakaoEntity(Long id, String email, String name, String youtubeLink, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.youtubeLink = youtubeLink;
        this.role = role;
    }


    public KakaoEntity update(String name, String email) {
        this.name = name;
        this.email = email;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
