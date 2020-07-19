package com.soriel.music.springboot.web.dto.posts;

import com.soriel.music.springboot.domain.soriel.IntegrationEntity;
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
    private IntegrationEntity integrationEntity_id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostsEntity toEntity() {
        return PostsEntity.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .category(category)
                .integrationEntity(integrationEntity_id)
                .verify_reply(verify_reply)
                .build();
    }

    @Builder
    public PostsDto(Long id, String title, String category, String content, String writer,
                    String verify_reply, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.writer = writer;
        this.verify_reply = verify_reply;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
