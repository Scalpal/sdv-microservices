package com.example.sdvstudentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SdvStudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdvStudentApiApplication.class, args);
    }

}
