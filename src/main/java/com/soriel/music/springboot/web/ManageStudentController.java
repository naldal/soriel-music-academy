package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.student.Students;
import com.soriel.music.springboot.service.soriel.StudentsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class ManageStudentController {

    private StudentsService studentsService;

    @GetMapping("/admin/manageStudent")
    public String dis_manage(Model model){
        List<Students> studentsList = studentsService.findAllStudents();

        for(Students std: studentsList){
            System.out.println(std.toString());
        }

        model.addAttribute("students", studentsList);
        return "soriel_Manage_student";
    }


}
