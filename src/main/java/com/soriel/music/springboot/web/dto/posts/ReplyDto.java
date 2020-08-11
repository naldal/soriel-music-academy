package com.soriel.music.springboot.web.dto.posts;

import com.soriel.music.springboot.domain.posts.PostsEntity;
import com.soriel.music.springboot.domain.posts.ReplyEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReplyDto {

    private Long id;
    private String reply_writer;
    private String reply_content;
    private PostsEntity post_id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ReplyDto(Long id, String reply_content,
                    String reply_writer, PostsEntity post_id,
                    LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.reply_content = reply_content;
        this.reply_writer = reply_writer;
        this.post_id = post_id;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public ReplyEntity toEntity() {
        return ReplyEntity.builder()
                .reply_content(reply_content)
                .reply_writer(reply_writer)
                .post_id(post_id)
                .build();
    }

}
