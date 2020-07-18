package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.soriel.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;


}
