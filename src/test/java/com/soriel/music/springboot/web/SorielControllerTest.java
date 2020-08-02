package com.soriel.music.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SorielControllerTest {

    @Autowired
    MockMvc mockMvc;

    /*@Test
    public void mainView() throws Exception {
        mockMvc.perform(get("/")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void picbrdView() throws Exception {
        mockMvc.perform(get("/picture_board")
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }

    @Test
    public void vidbrdView() throws Exception {
        mockMvc.perform(get("/video_board/1")
                .content("application/json"))
                .andExpect(status().isOk());
    }*/
}
