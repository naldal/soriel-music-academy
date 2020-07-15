package com.soriel.music.springboot.web;

import com.soriel.music.springboot.service.soriel.MemberService;
import com.soriel.music.springboot.web.dto.soriels.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    //로그인 페이지
    @GetMapping("/user/login_page")
    public String login_page() {
        return "soriel_Login_page";
    }

    //회원가입 페이지
    @GetMapping("/user/sign_up_page")
    public String sign_up_page() {
        return "soriel_Sign_up";
    }

    //회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login_page";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }

}
