package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.student.Students;
import com.soriel.music.springboot.domain.student.StudentsRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public List<Students> findAllByName() {
        List<Students> list = studentsRepository.findAllByName();
        return list;
    }

    public List<Students> findAllByNumber() {
        List<Students> list = studentsRepository.findAllById();
        return list;
    }

    public Students findOneById(Long id) {
        Optional<Students> studentsOptional = studentsRepository.findById(id);
        return studentsOptional.isPresent() ? studentsOptional.get() : null;
    }

    public Students updateStudent(Long id, Students students) {
        Optional<Students> optionalStudents = studentsRepository.findById(id);
        Students targetStudent = optionalStudents.get();

        targetStudent.update_student_info(students.getStudent_name(), students.getYoutube_link());
        return targetStudent;
    }

    public Boolean deleteStudent(Long id) {
        try {
            studentsRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void enroll(Students students) {
        studentsRepository.save(students);
    }
}
