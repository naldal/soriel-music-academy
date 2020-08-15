package com.soriel.music.springboot.web;

import com.soriel.music.springboot.service.soriel.MemberService;
import com.soriel.music.springboot.web.dto.member.IntegrationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

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
    public String execSignup(IntegrationDto memberDto) {
        memberService.joinUser(memberDto);
        return "redirect:/user/login_page";
    }

    //아이디 중복체크
    @GetMapping("/verify_id/{name}")
    @ResponseBody
    public boolean verify_id(@PathVariable("name") String name) {
        boolean flag = memberService.verifyId(name);
        return flag;
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "admin";
    }

}
