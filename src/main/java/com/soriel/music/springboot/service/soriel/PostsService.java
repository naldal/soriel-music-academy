package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.posts.PostsEntity;
import com.soriel.music.springboot.domain.posts.PostsRepository;
import com.soriel.music.springboot.domain.posts.ReplyEntity;
import com.soriel.music.springboot.domain.posts.ReplyRepository;
import com.soriel.music.springboot.web.dto.posts.PostsDto;
import com.soriel.music.springboot.web.dto.posts.PostsUpdateRequestDto;
import com.soriel.music.springboot.web.dto.posts.ReplyDto;
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
    private final ReplyRepository replyRepository;

    public Long savePosts(PostsDto postsDto) {

        return postsRepository.save(postsDto.toEntity()).getId();
    }

    public Page<PostsEntity> getPostList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
        pageable = PageRequest.of(page, 5, new Sort(Sort.Direction.DESC, "id"));

        return postsRepository.findAll(pageable);
    }

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

        postsEntity.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Optional<PostsEntity> postsEntityOptional = postsRepository.findById(id);
        PostsEntity postsEntity = postsEntityOptional.get();

        postsRepository.delete(postsEntity);
    }

    /* reply service */
    public Long save_reply(Long post_id, ReplyDto replyDto) {
        PostsEntity postsEntity = postsRepository.findById(post_id).orElseThrow(NullPointerException::new);
        postsEntity.doneReply();
        replyDto.setPost_id(postsEntity);

        return replyRepository.save(replyDto.toEntity()).getId();
    }

    public ReplyDto getReply(Long id) {
        ReplyEntity replyEntity = replyRepository.findByPostId(id).orElseThrow(NullPointerException::new);

        return ReplyDto.builder()
                .id(replyEntity.getId())
                .reply_content(replyEntity.getReply_content())
                .reply_writer(replyEntity.getReply_writer())
                .post_id(replyEntity.getPost_id())
                .createdDate(replyEntity.getCreatedDate())
                .modifiedDate(replyEntity.getModifiedDate())
                .build();
    }

    @Transactional
    public void deleteReply(Long reply_id) {
        ReplyEntity replyEntity = replyRepository.findById(reply_id).orElseThrow(NullPointerException::new);
        PostsEntity post_id = replyEntity.getPost_id();

        PostsEntity postsEntity = postsRepository.findById(post_id.getId()).orElseThrow(NullPointerException::new);
        postsEntity.undoReply();

        replyRepository.deleteById(reply_id);
    }

    @Transactional
    public void update_reply(Long reply_id, ReplyDto replyDto) {
        Optional<ReplyEntity> optionalReplyEntity = replyRepository.findById(reply_id);
        ReplyEntity replyEntity = optionalReplyEntity.get();

        replyEntity.update(replyDto.getReply_content());
    }
}
