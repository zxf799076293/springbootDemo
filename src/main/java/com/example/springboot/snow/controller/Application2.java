package com.example.springboot.snow.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/round2")
@RestController
public class Application2 {
    @RequestMapping(method = RequestMethod.GET)
    public String roundS2() {
        return "ROUND2";
    }
    @Value("${zzp.name}")
    private String name;

    @Value("${zzp.age}")
    private Integer age;

    @Value("${zzp.height}")
    private String height;

    @RequestMapping(value = "/zzp",method = RequestMethod.GET)
    public String getZZPInfo(){
        return "zzp.name:" + name + " zzp.age:" + age + " zzp.height:" + height;
    }

}
