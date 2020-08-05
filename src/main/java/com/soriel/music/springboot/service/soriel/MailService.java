package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.web.dto.mail.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "cec7777@naver.com";

    public void sendMail(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo("cec7777@naver.com");
        simpleMailMessage.setFrom(MailService.FROM_ADDRESS);
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText("please reply to this email : "+mailDto.getEmail()+"\n"+mailDto.getMessage());

        mailSender.send(simpleMailMessage);
    }

}
