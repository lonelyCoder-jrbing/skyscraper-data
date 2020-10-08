package com.skyscraper.data.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.skyscraper.data")
public class SkyscraperDataControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyscraperDataControllerApplication.class, args);
    }

}
