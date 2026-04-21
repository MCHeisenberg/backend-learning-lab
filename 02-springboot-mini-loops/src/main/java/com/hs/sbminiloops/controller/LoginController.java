package com.hs.sbminiloops.controller;

import com.hs.sbminiloops.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return "login request ok:  "+request.getUsername();
    }

}
