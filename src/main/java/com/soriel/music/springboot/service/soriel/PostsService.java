package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.soriel.PostsEntity;
import com.soriel.music.springboot.domain.soriel.PostsRepository;
import com.soriel.music.springboot.web.dto.posts.PostsDto;
import com.soriel.music.springboot.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    /*@Transactional
    public Page<PostsDto> getPostList(Pageable pageable) {
        Page<PostsEntity> postsEntities = postsRepository.findAll(pageable);
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
    }*/

    @Transactional
    public Page<PostsEntity> getPostList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
        pageable = PageRequest.of(page, 5, new Sort(Sort.Direction.DESC, "id"));

        return postsRepository.findAll(pageable);
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
                            .writer_id(postsEntity.getWriter_id())
                            .createdDate(postsEntity.getCreatedDate())
                            .modifiedDate(postsEntity.getModifiedDate())
                            .build();

        return postsDto;
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Optional<PostsEntity> postsEntityOptional = postsRepository.findById(id);
        PostsEntity postsEntity = postsEntityOptional.get();

        System.out.println("updated content :::::"+requestDto.getContent());
        postsEntity.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Optional<PostsEntity> postsEntityOptional = postsRepository.findById(id);
        PostsEntity postsEntity = postsEntityOptional.get();

        postsRepository.delete(postsEntity);
    }

    @Transactional
    public int findAllPostNum() {
        List<PostsEntity> postsEntityList = postsRepository.findAll();

        return postsEntityList.size();
    }

}
