package com.example.sbpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class SbProApplication {
    private static final Logger log = LoggerFactory.getLogger(SbProApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SbProApplication.class, args);
    }

}

