package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.Role;
import com.soriel.music.springboot.domain.posts.PostsEntity;
import com.soriel.music.springboot.service.soriel.MemberService;
import com.soriel.music.springboot.service.soriel.PostsService;
import com.soriel.music.springboot.web.dto.posts.PostsDto;
import com.soriel.music.springboot.web.dto.posts.PostsUpdateRequestDto;
import com.soriel.music.springboot.web.dto.posts.ReplyDto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

        if(postsDto.getTitle()==null || postsDto.getContent()==null || writerId==null) {
            return "redirect:/error";
        } else {
            postsDto.setWriter_id(writerId);
            postsDto.setWriter(authentication.getName());
            postsService.savePosts(postsDto);

            return "redirect:/inquire_board";
        }
    }

    //게시판 리스트 이동
    @GetMapping("/inquire_board")
    public String postList(Model model, @PageableDefault Pageable pageable) {
        Page<PostsEntity> postList = postsService.getPostList(pageable);
       /* System.out.println("---postList---");
        System.out.println("number : "+postList.getNumber());
        System.out.println("totalpage : "+postList.getTotalPages());
        System.out.println("size : "+postList.getSize());
        System.out.println("sort : "+postList.getSort());
        System.out.println("first : "+postList.isFirst());
        System.out.println("last : "+postList.isLast());
        System.out.println("content : "+postList.getContent());
        model.addAttribute("postList", postList);*/
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
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Long authenticationId = memberService.getMemberInfo(authentication.getName());
        if (authenticationId == null) return "soriel_Login_page";

        PostsDto postsDto = postsService.getPost(id);
        ReplyDto replyDto;
        try {
            replyDto = postsService.getReply(id);
        } catch (NullPointerException e) {
            replyDto = null;
        }

        model.addAttribute("postDto", postsDto);
        model.addAttribute("replyDto", replyDto);

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
    public void update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        //현재 로그인 한 id
        Long current_id = memberService.getMemberInfo(authentication.getName());
        if(!requestDto.getWriter_id().equals(current_id)) return;

        //글번호, json
        postsService.update(id, requestDto);
    }

    //게시글 삭제처리
    @DeleteMapping("/post/delete/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("getname:::"+authentication.getName());
        PostsDto postsDto = postsService.getPost(id);

        //현재 로그인 한 id
        Long current_id = memberService.getMemberInfo(authentication.getName());
        if(postsDto.getWriter_id().equals(current_id) || authentication.getName().equals("admin")){
            postsService.delete(id);
        } else if(!postsDto.getWriter_id().equals(current_id)) {
            return false;
        }

        return true;
    }

    //답장 등록 기능
    @PostMapping("/post/reply/{post_id}")
    @ResponseBody
    public Boolean reply_function(@PathVariable("post_id") Long post_id, @RequestBody ReplyDto replyDto) {
        System.out.println(":::"+post_id);
        System.out.println("::::"+replyDto.toString());
        replyDto.setReply_writer("administrator");
        return postsService.save_reply(post_id, replyDto)>=0L;
    }

    //답장 삭제 기능
    @DeleteMapping("/post/reply/{reply_id}")
    @ResponseBody
    public Boolean reply_delete(@PathVariable("reply_id") Long reply_id) {
        postsService.deleteReply(reply_id);
        return true;
    }

    //답글 수정 기능
    @PutMapping("/post/reply/{reply_id}")
    @ResponseBody
    public Long update_reply(@PathVariable("reply_id") Long reply_id, @RequestBody ReplyDto replyDto) {
        postsService.update_reply(reply_id, replyDto);
        return reply_id;
    }
}
