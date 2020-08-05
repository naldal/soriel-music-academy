package com.soriel.music.springboot.web;

import com.soriel.music.springboot.service.soriel.MailService;
import com.soriel.music.springboot.web.dto.mail.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.AddressException;

@Controller
@AllArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/contact")
    public String dis_contact() {
        return "soriel_contact_us";
    }

    @PostMapping("/mail")
    public String execMail(MailDto mailDto) throws AddressException {
        mailService.sendMail(mailDto);
        return "soriel_contact_us";
    }
}
