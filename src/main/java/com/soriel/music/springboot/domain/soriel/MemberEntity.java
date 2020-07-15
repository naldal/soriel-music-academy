package com.soriel.music.springboot.domain.soriel;

import com.soriel.music.springboot.domain.BaseTimeEntity;
import com.soriel.music.springboot.domain.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 200)
    private String upwd;

    @Column(nullable = true, length = 200)
    private String youtubeLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Builder
    public MemberEntity(Long id, String email, String name, String upwd, String youtubeLink, Role role) {
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


}
