package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.soriel.PostsEntity;
import com.soriel.music.springboot.service.soriel.MemberService;
import com.soriel.music.springboot.service.soriel.PostsService;
import com.soriel.music.springboot.web.dto.posts.PostsDto;
import com.soriel.music.springboot.web.dto.posts.PostsUpdateRequestDto;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

    private final PostsService postsService;
    private final MemberService memberService;
    private Authentication authentication;

    //게시판 글쓰기 기능
    @PostMapping("/post")
    public String write(PostsDto postsDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();

        Long writerId = memberService.getMemberInfo(authentication.getName());
        System.out.println(writerId);

        postsDto.setWriter_id(writerId);
        postsDto.setWriter(authentication.getName());
        postsService.savePosts(postsDto);

        return "redirect:/inquire_board";
    }

    //게시판 리스트 이동
    @GetMapping("/inquire_board")
    public String postList(Model model, @PageableDefault Pageable pageable) {
        Page<PostsEntity> postList = postsService.getPostList(pageable);

        model.addAttribute("postList", postList);
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
        System.out.println(postsDto.toString());
        model.addAttribute("postDto", postsDto);

        return "soriel_Inquiry_view";
    }

    //게시글 수정 페이지로 이동
    @GetMapping("/post/update/{id}")
    public String dis_update(@PathVariable("id") Long id, Model model){
        authentication = SecurityContextHolder.getContext().getAuthentication();

        PostsDto postsDto = postsService.getPost(id);

        model.addAttribute("postDto", postsDto);

        return "soriel_Inquiry_update";
    }

    //게시글 수정 기능
    @PutMapping("/post/update/{id}")
    @ResponseBody
    public Boolean update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        //현재 로그인 한 id
        Long current_id = memberService.getMemberInfo(authentication.getName());

        System.out.println("writer id :"+requestDto.getWriter_id());
        System.out.println("curr id :"+current_id);

        if(requestDto.getWriter_id() != current_id) {
            return false;
        }

        postsService.update(id, requestDto);
        //return "redirect:/inquire_view/"+id;
        return true;
    }

    @DeleteMapping("/post/delete/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id){
        authentication = SecurityContextHolder.getContext().getAuthentication();

        PostsDto postsDto = postsService.getPost(id);

        //현재 로그인 한 id
        Long current_id = memberService.getMemberInfo(authentication.getName());
        if(postsDto.getWriter_id() != current_id) {
            return false;
        }

        postsService.delete(id);

        return true;
    }


}
