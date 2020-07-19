package com.soriel.music.springboot.web.dto.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private Long id;
    private String title;
    private String content;
    private Long writer_id;

    @Builder
    public PostsUpdateRequestDto(Long id, String title, String content, Long writer_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer_id = writer_id;
    }
}
