package com.hs.sbminiloops.controller;

import com.hs.sbminiloops.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest request){
        if(request.getUsername()==null || request.getUsername().isBlank()){
            return "username is empty";
        }
        if(request.getPassword()==null || request.getPassword().isBlank()){
            return "password is empty";
        }
        return "login request ok:  "+request.getUsername()+", age = "+request.getAge();
    }

}
