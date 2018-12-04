package com.example.springboot.snow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/hello"})
public class HelloController {
    @GetMapping("/login")
    public String hello() {
//        request.setAttribute("key", "hello world");
        return "login";
    }
}
