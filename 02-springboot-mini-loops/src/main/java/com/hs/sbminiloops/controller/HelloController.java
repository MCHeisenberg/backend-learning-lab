package com.hs.sbminiloops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        return "hello 嘿嘿," + name;
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye!";
    }
}
