package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TimeController {

    @GetMapping("/time")
    public String getTime() {
        return "Current time is: " + LocalDateTime.now();
    }
}
