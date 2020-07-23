package com.soriel.music.springboot.domain.web;

import com.soriel.music.springboot.domain.soriel.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SorielControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void url_Approach_test() {
        //when
        String main_body = this.testRestTemplate.getForObject("/", String.class);
        String login_body = this.testRestTemplate.getForObject("/login_page", String.class);
        String picture_body = this.testRestTemplate.getForObject("/picture_board", String.class);
        String video_body = this.testRestTemplate.getForObject("/video_board", String.class);
        String inquire_body = this.testRestTemplate.getForObject("/inquire_board", String.class);
        String inquire_form_body = this.testRestTemplate.getForObject("/inquire_form", String.class);
        //then
        assertThat(main_body).contains("Soriel 피아노 철학");
//        assertThat(login_body).contains("Forgot Password?");
        assertThat(picture_body).contains("Soriel 학원의 사진들입니다");
        assertThat(video_body).contains("Soriel 의 비디오 게시판 입니다.");
        assertThat(inquire_body).contains("문의게시판");
        assertThat(inquire_form_body).contains("궁금하거나 도움이 필요한 것이 있으신가요?");
    }

}
