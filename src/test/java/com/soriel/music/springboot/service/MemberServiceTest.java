package com.soriel.music.springboot.service;

import com.soriel.music.springboot.domain.posts.PostsEntity;
import com.soriel.music.springboot.service.soriel.PostsService;
import com.soriel.music.springboot.web.dto.posts.PostsDto;
import com.soriel.music.springboot.web.dto.posts.PostsUpdateRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    HttpSession httpSession;

    private PostsDto postsDto;

    @Before
    public void setUp() {
        postsDto = postsDto.builder()
                    .id(1L)
                    .title("title")
                    .content("content")
                    .category("category")
                    .writer("writer")
                    .writer_id(1L)
                    .build();

        postsService.savePosts(postsDto);
    }

    @Test
    public void post_조회() {
        assertThat(postsService.getPost(postsDto.getId())).isNotNull();
    }

    /*@Test
    public void post_업데이트() {
        //given
        String expected_title = "update_title";
        String expected_content = "update_content";
        Long writer_id = 1L;
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expected_title).content(expected_content)
                .writer_id(writer_id).build();

        System.out.println("session id:" +httpSession.getId());

        //when
        postsService.update(postsDto.getId(), requestDto);

        //then
        assertThat(postsDto.getTitle()).isEqualTo(expected_title);
        assertThat(postsDto.getContent()).isEqualTo(expected_content);
    }*/
}
