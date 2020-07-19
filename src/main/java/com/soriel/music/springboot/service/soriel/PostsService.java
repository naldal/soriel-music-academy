package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.soriel.PostsEntity;
import com.soriel.music.springboot.domain.soriel.PostsRepository;
import com.soriel.music.springboot.web.dto.posts.PostsDto;
import com.soriel.music.springboot.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long savePosts(PostsDto postsDto) {
        return postsRepository.save(postsDto.toEntity()).getId();
    }

    @Transactional
    public List<PostsDto> getPostList() {
        List<PostsEntity> postsEntities = postsRepository.findAll();
        List<PostsDto> postsDtoList = new ArrayList<>();

        for (PostsEntity postsEntity : postsEntities) {
            PostsDto postsDto = PostsDto.builder()
                    .id(postsEntity.getId())
                    .title(postsEntity.getTitle())
                    .category(postsEntity.getCategory())
                    .writer(postsEntity.getWriter())
                    .content(postsEntity.getContent())
                    .createdDate(postsEntity.getCreatedDate())
                    .modifiedDate(postsEntity.getModifiedDate())
                    .build();

            postsDtoList.add(postsDto);
        }

        return postsDtoList;
    }

    @Transactional
    public PostsDto getPost(Long id) {
        Optional<PostsEntity> postsEntityOptional = postsRepository.findById(id);
        PostsEntity postsEntity = postsEntityOptional.get();

        PostsDto postsDto = PostsDto.builder()
                .id(postsEntity.getId())
                .title(postsEntity.getTitle())
                .writer(postsEntity.getWriter())
                .content(postsEntity.getContent())
                .category(postsEntity.getCategory())
                .createdDate(postsEntity.getCreatedDate())
                .modifiedDate(postsEntity.getModifiedDate())
                .build();

        return postsDto;
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Optional<PostsEntity> postsEntityOptional = postsRepository.findById(id);
        PostsEntity postsEntity = postsEntityOptional.get();

        postsEntity.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
}
