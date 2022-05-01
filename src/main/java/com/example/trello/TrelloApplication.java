package com.example.trello;

import com.example.trello.service.UploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
