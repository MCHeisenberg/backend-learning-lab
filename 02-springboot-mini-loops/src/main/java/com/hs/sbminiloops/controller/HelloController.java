package com.hs.sbminiloops.controller;

import com.hs.sbminiloops.response.HelloResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name,
                        Integer age){
        if( (name == null || name.isBlank()) && (age == null) ){
            return "hello 嘿嘿";
        } else if (name == null || name.isBlank()) {
            return "hello 嘿嘿, "+"age: "+age;
        } else if (age == null) {
            return "hello 嘿嘿, "+name;
        }
        return "hello 嘿嘿,"+name+",age = "+age;
    }

    @GetMapping("/helloDefault")
    public String helloDefault(@RequestParam(defaultValue = "guest")String name){
        return "hello,"+name;
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye!";
    }

    @GetMapping("/helloResponse")
    public HelloResponse hello(){
        return new HelloResponse("nice to meet you","hs");
    }
}
