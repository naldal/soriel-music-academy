package com.soriel.music.springboot.web.dto.gallery;

import com.soriel.music.springboot.domain.gallery.Gallery;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {

    private Long id;
    private String title;
    private String filePath;

    @Builder
    public GalleryDto(Long id, String title, String filePath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
    }

    public Gallery toEntity() {
        return Gallery.builder()
                .id(id)
                .title(title)
                .filePath(filePath)
                .build();
    }
}
