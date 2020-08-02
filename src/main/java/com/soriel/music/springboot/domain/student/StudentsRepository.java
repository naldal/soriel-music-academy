package com.soriel.music.springboot.domain.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Students, Long> {
    @Query(value = "select * from students order by student_name asc", nativeQuery = true)
    List<Students> findAll();
}
