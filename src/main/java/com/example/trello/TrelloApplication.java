package com.example.trello;

import com.example.trello.service.UploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@SpringBootApplication
public class TrelloApplication implements CommandLineRunner {

    @Resource
    UploadService uploadService;

    public static void main(String[] args) {
        SpringApplication.run(TrelloApplication.class, args);
    }


    @Override
    public void run(String... arg) {
        uploadService.init();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
                        .allowCredentials(true);
            }
        };
    }
}
