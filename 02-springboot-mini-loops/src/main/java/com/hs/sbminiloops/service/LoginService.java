package com.hs.sbminiloops.service;

import com.hs.sbminiloops.request.LoginRequest;
import com.hs.sbminiloops.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public LoginResponse login(LoginRequest request){
        if(request.getUsername()==null || request.getUsername().isBlank()){
            return new LoginResponse(false, "username is empty", null, null);
        }
        if(request.getPassword()==null || request.getPassword().isBlank()){
            return new LoginResponse(false,"password is empty",request.getUsername(),null);
        }

        return new LoginResponse(true,"login request ok",request.getUsername(), request.getAge());
    }
    public LoginResponse mock(){
        return new LoginResponse(true,"mock is ok","duke",888);
    }
}
