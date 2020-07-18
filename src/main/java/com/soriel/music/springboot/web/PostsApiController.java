package com.soriel.music.springboot.web;

import com.soriel.music.springboot.domain.soriel.PostsEntity;
import com.soriel.music.springboot.service.soriel.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/post")
    public void write(PostsEntity postsEntity) {

    }

}
