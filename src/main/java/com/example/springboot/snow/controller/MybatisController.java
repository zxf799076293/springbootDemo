package com.example.springboot.snow.controller;

import com.example.springboot.snow.pojo.UserInfo;
import com.example.springboot.snow.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private MybatisService service;


}
