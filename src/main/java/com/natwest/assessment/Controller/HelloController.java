package com.natwest.assessment.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}