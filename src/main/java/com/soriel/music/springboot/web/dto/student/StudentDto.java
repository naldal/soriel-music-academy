package com.soriel.music.springboot.web.dto.student;

import com.soriel.music.springboot.domain.student.Students;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentDto {

    private String student_name;
    private String youtube_link;

    @Builder
    public StudentDto(String student_name, String youtube_link) {
        this.student_name = student_name;
        this.youtube_link = youtube_link;
    }

    public Students toEntity() {
        return Students.builder()
                .student_name(student_name)
                .youtube_link(youtube_link)
                .build();
    }

}
