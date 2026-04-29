package com.hs.sbminiloops.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String buildHelloText(String name){
        return "hello, "+name;
    }
}
