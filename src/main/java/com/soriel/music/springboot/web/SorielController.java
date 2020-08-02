package com.soriel.music.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SorielController {

    //메인 페이지
    @GetMapping("/")
    public String mainPage() {
        return "soriel_Main_page";
    }

    @GetMapping("/contact")
    public String dis_contact() {
        return "soriel_contact_us";
    }

    //사진 게시판
    @GetMapping("/picture_board")
    public String picture_board() {
        return "soriel_Picture_board";
    }

}
