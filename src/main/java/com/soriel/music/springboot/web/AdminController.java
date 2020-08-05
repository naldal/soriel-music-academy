package com.soriel.music.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String dis_admin() {
        return "admin";
    }

    @GetMapping("/admin/gallery")
    public String dis_gall(){
        return "gallery";
    }

    @GetMapping("/admin/manageStudent")
    public String dis_manage(){
        return "soriel_Manage_student";
    }

}
