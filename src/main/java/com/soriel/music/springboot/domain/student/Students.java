package com.soriel.music.springboot.domain.student;

import com.soriel.music.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Students extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String student_name;

    @Column(length = 255)
    private String youtube_link;

    public void update_student_info(String student_name, String youtube_link) {
        if(student_name.equals("")) {
            update_student_link(youtube_link);
        } else if(youtube_link.equals("")) {
            update_student_name(student_name);
        } else {
            this.student_name = student_name;
            this.youtube_link = youtube_link;
        }
    }

    public void update_student_name(String student_name){
        this.student_name = student_name;
    }

    public void update_student_link(String youtube_link){
        this.youtube_link = youtube_link;
    }

    @Builder
    public Students(String student_name, String youtube_link) {
        this.student_name = student_name;
        this.youtube_link = youtube_link;
    }
}
