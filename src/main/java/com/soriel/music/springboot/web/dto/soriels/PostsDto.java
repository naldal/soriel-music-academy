package com.soriel.music.springboot.web.dto.soriels;

import com.soriel.music.springboot.domain.soriel.PostsEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostsDto {

    private Long id;
    private String title;
    private String category;
    private String content;
    private String writer;
    private String verify_reply;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostsEntity toEntity() {
        return PostsEntity.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
    }

    @Builder
    public PostsDto(Long id, String title, String category, String content, String writer, String verify_reply, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.writer = writer;
        this.verify_reply = verify_reply;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
