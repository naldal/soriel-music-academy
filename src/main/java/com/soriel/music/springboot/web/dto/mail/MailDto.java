package com.soriel.music.springboot.web.dto.mail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MailDto {

    private String title;
    private String email;
    private String message;
}
