package com.soriel.music.springboot.web;

import com.soriel.music.springboot.service.soriel.GalleryService;
import com.soriel.music.springboot.service.soriel.S3Service;
import com.soriel.music.springboot.web.dto.gallery.GalleryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class GalleryController {

    private S3Service s3Service;
    private GalleryService galleryService;

    @GetMapping("/gallery")
    public String dispGal() {
        return "gallery";
    }

    @PostMapping("/gallery")
    public String execGal(GalleryDto galleryDto, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(file);
        galleryDto.setFilePath(imgPath);

        galleryService.savePath(galleryDto);
        return "redirect:/gallery";
    }
}
