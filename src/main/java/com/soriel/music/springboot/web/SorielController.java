package com.soriel.music.springboot.web;

import com.soriel.music.springboot.web.dto.mail.MailDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SorielController {

    //메인 페이지
    @GetMapping("/")
    public String mainPage() {
        return "soriel_Main_page";
    }

    //사진 게시판
    @GetMapping("/picture_board")
    public String picture_board() {
        return "soriel_Picture_board";
    }

}
