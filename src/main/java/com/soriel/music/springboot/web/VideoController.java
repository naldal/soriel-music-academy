package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.student.Students;
import com.soriel.music.springboot.service.soriel.StudentsService;
import java.util.List;

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
    @GetMapping("/video_board")
    public String video_board(Model model) {
        List<Students> studentsList = studentsService.findAllStudents();
        model.addAttribute("students", studentsList);
        return "soriel_Video_board";
    }

    //학생별 비디오
    @PostMapping("/video_board/{id}")
    @ResponseBody
    public Boolean get_video(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {

        studentsService.findOneById(id);
        return true;
    }

}
