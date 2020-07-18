package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.soriel.PostsEntity;
import com.soriel.music.springboot.service.soriel.PostsService;
import com.soriel.music.springboot.web.dto.posts.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

    private final PostsService postsService;
    private Authentication authentication;

    //게시판 글쓰기 기능
    @PostMapping("/post")
    public String write(PostsDto postsDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(">>>>>>>>>>>>>>>>>>>"+authentication.getName());
        postsDto.setWriter(authentication.getName());
        postsService.savePosts(postsDto);

        return "redirect:/inquire_board";
    }

    //게시판 리스트 이동
    @GetMapping("/inquire_board")
    public String postList(Model model) {
        List<PostsDto> postsList = postsService.getPostList();

        model.addAttribute("postList", postsList);
        return "soriel_Inquire_board";
    }

    //문의 양식 페이지
    @GetMapping("/inquire_form")
    public String inquire_form() {
        return "soriel_Form_Inquiry";
    }

    //문의 게시물 보기
    @GetMapping("/inquire_view/{id}")
    public String inquire_view(@PathVariable("id") Long id, Model model) {
        PostsDto postsDto = postsService.getPost(id);

        model.addAttribute("postDto", postsDto);

        return "soriel_Inquiry_view";
    }
}
