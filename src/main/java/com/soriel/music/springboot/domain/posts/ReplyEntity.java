package com.soriel.music.springboot.domain.posts;

import com.soriel.music.springboot.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "reply")
@NoArgsConstructor
public class ReplyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String reply_writer;

    @Column(length = 255, nullable = false)
    private String reply_content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostsEntity post_id;

    @Builder
    public ReplyEntity(Long id, String reply_content, String reply_writer, PostsEntity post_id) {
        this.id = id;
        this.reply_content = reply_content;
        this.reply_writer = reply_writer;
        this.post_id = post_id;
    }

    public void update(String reply_content) {
        this.reply_content = reply_content;
    }

}
