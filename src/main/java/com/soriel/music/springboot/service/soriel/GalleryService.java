package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.gallery.GalleryRepository;
import com.soriel.music.springboot.web.dto.gallery.GalleryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GalleryService {

    private GalleryRepository galleryRepository;

    public void savePath(GalleryDto galleryDto) {
        galleryRepository.save(galleryDto.toEntity());
    }

}
