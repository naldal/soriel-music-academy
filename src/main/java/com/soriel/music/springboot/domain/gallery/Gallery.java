package com.soriel.music.springboot.domain.gallery;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    @Builder
    public Gallery(Long id, String title, String filePath){
        this.id = id;
        this.title = title;
        this.filePath = filePath;
    }
}
