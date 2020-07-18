package com.soriel.music.springboot.domain.soriel;

import com.soriel.music.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PostsEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column
    private String category;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String writer;

    @Column(nullable = true)
    private String verify_reply;

    @Builder
    public PostsEntity(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void reply_update(String verify_reply) {
        this.verify_reply = verify_reply;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
