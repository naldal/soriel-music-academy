package com.soriel.music.springboot.web.dto.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostsUpdateRequestDto {

    private String title;
    private String content;
    private Long writer_id;

    @Builder
    public PostsUpdateRequestDto(String title, String content, Long writer_id) {
        this.title = title;
        this.content = content;
        this.writer_id = writer_id;
    }
}
