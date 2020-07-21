package com.soriel.music.springboot;

import com.soriel.music.springboot.domain.Role;
import com.soriel.music.springboot.domain.soriel.IntegrationEntity;
import com.soriel.music.springboot.domain.soriel.IntegrationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
