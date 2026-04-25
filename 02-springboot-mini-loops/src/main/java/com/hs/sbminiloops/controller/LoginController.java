package com.hs.sbminiloops.controller;

import com.hs.sbminiloops.request.LoginRequest;
import com.hs.sbminiloops.response.LoginResponse;
import com.hs.sbminiloops.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController{
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }

    public LoginResponse login(@RequestBody LoginRequest request){
        return loginService.login(request);
    }

}


//@RestController
//public class LoginController {
//    @PostMapping("/auth/login")
//    public LoginResponse login(@RequestBody LoginRequest request){
//        if(request.getUsername()==null || request.getUsername().isBlank()){
//            return new LoginResponse(false,"username is empty", null,null);  //"username is empty";
//        }
//        if(request.getPassword()==null || request.getPassword().isBlank()){
//            return new LoginResponse(false,"password is empty", request.getUsername(), null);  //"password is empty";
//        }
//        //return "login request ok:  "+request.getUsername()+", age = "+request.getAge();
//        return new LoginResponse(true,"login request ok: ",request.getUsername(), request.getAge());
//    }
//
//    @PostMapping("/auth/mock")
//    public LoginResponse mock(){
//        return new LoginResponse(true,"mock is ok,","duke",888);
//    }
//}
