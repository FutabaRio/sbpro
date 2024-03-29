package com.example.sbpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SbProApplication {
    private static final Logger logger = LoggerFactory.getLogger(SbProApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SbProApplication.class, args);
    }

}

