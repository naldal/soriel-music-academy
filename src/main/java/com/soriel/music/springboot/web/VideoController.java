package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.student.Students;
import com.soriel.music.springboot.service.soriel.StudentsService;
import java.util.List;
import java.util.Optional;

import com.soriel.music.springboot.web.dto.student.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class VideoController {

    private final StudentsService studentsService;

    //비디오 게시판 진입
    @GetMapping("/video_board/{id}")
    public String video_board(@PathVariable("id") Long id, Model model, @RequestParam(required = false) String link) {
        List<Students> studentsList = studentsService.findAllStudents();

        model.addAttribute("link", link);
        model.addAttribute("students", studentsList);
        return "soriel_Video_board";
    }

    //학생별 비디오 (link 가공 후 ajax return)
    @PostMapping("/video_board/{id}")
    @ResponseBody
    public String get_link(@PathVariable("id") Long id) {
        Students students = studentsService.findOneById(id);
        String temp = students.getYoutube_link();

        int start = temp.indexOf("?v=");
        int end = temp.lastIndexOf("&");

        String ajaxReturn = end <= 0 ? temp.substring(start+3) : temp.substring(start+3, end);

        System.out.println("ajax return:"+ajaxReturn);
        return ajaxReturn;
    }

}
