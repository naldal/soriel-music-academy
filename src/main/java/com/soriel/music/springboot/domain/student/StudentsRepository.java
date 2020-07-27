package com.soriel.music.springboot.domain.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Students, Long> {
}
