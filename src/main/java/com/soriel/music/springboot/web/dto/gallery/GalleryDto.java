package com.soriel.music.springboot.web.dto.gallery;

import com.soriel.music.springboot.domain.gallery.Gallery;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {

    private Long id;
    private String url;

    @Builder
    public GalleryDto(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Gallery toEntity() {
        return Gallery.builder()
                .id(id)
                .url(url)
                .build();
    }

}
