package com.example.sbpro.controller;

import com.example.sbpro.controller.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello , %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Result<String> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return Result.success("greet name:" + name);
    }
}



