package com.soriel.music.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /* only run on local environment*/
    /*@Bean
    public CommandLineRunner initStudents(StudentsRepository studentsRepository, PostsRepository postsRepository, IntegrationRepository integrationRepository) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return args -> {
            IntegrationEntity integrationEntity = IntegrationEntity.builder()
                    .name("admin")
                    .upwd(passwordEncoder.encode("admin"))
                    .email("cec7777@naver.com")
                    .role(Role.ADMIN)
                    .build();

            integrationRepository.save(integrationEntity);
            IntStream.rangeClosed(1, 50).forEach(i -> {
                Students students = Students.builder()
                        .student_name("student" + i)
                        .youtube_link("youtube_link" + 1)
                        .build();

                studentsRepository.save(students);

            });

            IntStream.rangeClosed(1, 100).forEach(i -> {

                Long tempId = new Long(i);
                PostsEntity postsEntity = PostsEntity.builder()
                        .id(tempId)
                        .title("title"+i)
                        .category("")
                        .content("content")
                        .writer("test user")
                        .build();

                postsRepository.save(postsEntity);

            });
        };
    }*/
}
