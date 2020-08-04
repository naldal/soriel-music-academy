package com.soriel.music.springboot.web;

import com.soriel.music.springboot.service.soriel.GalleryService;
import com.soriel.music.springboot.service.soriel.S3Service;
import com.soriel.music.springboot.web.dto.gallery.GalleryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class GalleryController {

    private S3Service s3Service;

    @GetMapping("/gallery")
    public String dispGal() {
        return "gallery";
    }

    @PostMapping("/gallery")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return s3Service.upload(multipartFile, "static");
    }
}
