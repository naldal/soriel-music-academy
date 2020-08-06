package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.gallery.Gallery;
import com.soriel.music.springboot.service.soriel.S3Service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @GetMapping("/picture_board")
    public String dispicboard(Model model) {
        List<Gallery> list = s3Service.getPictures();
        model.addAttribute("picture_list", list);
        return "soriel_Picture_board";
    }

    @GetMapping("/admin/gallery")
    public String dispGal() {
        return "gallery";
    }

    @PostMapping("/admin/gallery")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        System.out.println("111:"+multipartFile.toString());
        return s3Service.upload(multipartFile, "static");
    }
}
