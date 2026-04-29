package com.hs.sbminiloops.service;

import com.hs.sbminiloops.request.LoginRequest;
import com.hs.sbminiloops.response.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class LoginService {

    public LoginResponse login(LoginRequest request){

        if(request == null){
            return new LoginResponse(false,"request is empty",null,null);
        }

        if(request.getAge()!=null && request.getAge()<0){
            return new LoginResponse(false,"age is invalid",request.getUsername(),null);
        }

        if(request.getUsername()==null || request.getUsername().isBlank()){
            return new LoginResponse(false, "username is empty", null, null);
        }
        if(request.getPassword()==null || request.getPassword().isBlank()){
            return new LoginResponse(false,"password is empty",request.getUsername(),null);
        }
        return new LoginResponse(true,"login request ok",request.getUsername(), request.getAge());
    }

    public LoginResponse mock(String name){
        return new LoginResponse(true,"mock is ok",name,888);
    }

    public LoginResponse registerMock(){
        return new LoginResponse(true,"register mock ok","new-user",20);
    }


}
