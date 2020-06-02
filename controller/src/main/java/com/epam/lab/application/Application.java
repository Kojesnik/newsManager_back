package com.epam.lab.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.epam.lab")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
