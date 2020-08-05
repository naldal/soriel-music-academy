package com.soriel.music.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String dis_admin() {
        return "admin";
    }

    @GetMapping("/admin/manageStudent")
    public String dis_manage(){
        return "soriel_Manage_student";
    }

}
