package com.soriel.music.springboot.domain.soriel;

import com.soriel.music.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public PostsEntity(Long id, String title, String writer, String content, String category, String verify_reply, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.category = category;
        this.verify_reply = verify_reply;
    }

    public void reply_update(String verify_reply) {
        this.verify_reply = verify_reply;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
