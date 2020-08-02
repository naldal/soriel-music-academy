package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.web.dto.mail.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "cec7777@naver.com";

    @Bean
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(mailDto.getEmail());
        simpleMailMessage.setFrom(MailService.FROM_ADDRESS);
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getMessage());

        System.out.println(simpleMailMessage.toString());
        mailSender.send(simpleMailMessage);
    }
}
