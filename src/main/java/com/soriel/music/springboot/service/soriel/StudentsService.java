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

    public List<Students> findAllStudents() {
        List<Students> list = studentsRepository.findAll();
        return list;
    }

    public void findOneById(Long id) {
        Optional<Students> var = studentsRepository.findById(id);
    }
}
