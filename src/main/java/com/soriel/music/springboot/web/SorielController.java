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

    //사진 게시판
    @GetMapping("/picture_board")
    public String picture_board() {
        return "soriel_Picture_board";
    }

    //비디오 게시판
    @GetMapping("/video_board")
    public String video_board() {
        return "soriel_Video_board";
    }

    //문의 게시판
    @GetMapping("/inquire_board")
    public String inquire_board() {
        return "soriel_Inquire_board";
    }

    //문의 양식 페이지
    @GetMapping("/inquire_form")
    public String inquire_form() {
        return "soriel_Form_Inquiry";
    }

    //문의 게시물 보기
    @GetMapping("/inquire_view")
    public String inquire_view() {
        return "soriel_Inquiry_view";
    }

}
