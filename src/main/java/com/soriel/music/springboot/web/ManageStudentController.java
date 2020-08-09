package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.student.Students;
import com.soriel.music.springboot.service.soriel.StudentsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ManageStudentController {

    private StudentsService studentsService;

    @GetMapping("/admin/manage")
    public String dis_manage(Model model, @RequestParam(required = false) String sort){
        List<Students> studentsList = null;
        if(sort==null || sort.equals("name")) {
            studentsList = studentsService.findAllByName();
        }else if(sort.equals("number")) {
            studentsList = studentsService.findAllByNumber();
        }

        model.addAttribute("students", studentsList);
        return "soriel_Manage_student";
    }

    @PostMapping("/admin/manage/{id}")
    @ResponseBody
    public Students update_student(@PathVariable("id") Long id, Model model, @RequestBody Students students){
        Students updatedStudent = studentsService.updateStudent(id, students);
        return updatedStudent;
    }

    @DeleteMapping("/admin/manage/{id}")
    @ResponseBody
    public Boolean delete_student(@PathVariable("id") Long id) {
        return studentsService.deleteStudent(id);
    }

    @PostMapping("/admin/manage/enroll")
    @ResponseBody
    public void enroll_student(@RequestBody Students students){
        studentsService.enroll(students);
    }
}
