package com.hs.sbminiloops.service;

import com.hs.sbminiloops.request.LoginRequest;
import com.hs.sbminiloops.response.LoginResponse;
import com.hs.sbminiloops.response.Result;
import org.springframework.stereotype.Service;

@Service
public class LoginService{

    //block06 变式2：新增一个失败版 mock
    public Result<LoginResponse> mockFail(){
        //return new Result<>(false,"mock failed",null);
        return Result.fail("mock failed");
    }

    public Result<LoginResponse> login(LoginRequest request){
        if(request == null){
            //return new Result<>(false,"request is empty",null);
            return Result.fail("request is empty");
        }

        if(request.getUsername()==null || request.getUsername().isBlank()){
            //return new Result<>(false,"username is empty",null);
            return Result.fail("username is empty");
        }

        if(request.getPassword()==null || request.getPassword().isBlank()){
            //return new Result<>(false,"password is empty",null);
            return Result.fail("password is empty");
        }

        if(request.getAge()!=null && request.getAge()<0){
            //return new Result<>(false,"age is invalid",null);
            return Result.fail("age is invalid");
        }

        LoginResponse data = new LoginResponse(request.getUsername(), request.getAge());
        //return new Result<>(true, "login request ok", data);
        return Result.success("login request ok",data);
    }

    public Result<LoginResponse> mock(String name){
        //b6 加强 A：把校验逻辑继续放回 Service
        if(name==null || name.isBlank()){
            return Result.fail("name is empty");
        }
        LoginResponse data = new LoginResponse(name,888);
        //return new Result<>(true,"mock ok",data);
        return Result.success("mock ok",data);
    }

    public Result<LoginResponse> registerMock(){
        LoginResponse data = new LoginResponse("new-user",20);
        //return new Result<>(true,"regidter mock ok",data);
        return Result.success("register mock ok",data);
    }

}


//blo 1-5
//import com.hs.sbminiloops.request.LoginRequest;
//import com.hs.sbminiloops.response.LoginResponse;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Service
//public class LoginService {
//
//    public LoginResponse login(LoginRequest request){
//
//        if(request == null){
//            return new LoginResponse(false,"request is empty",null,null);
//        }
//
//        if(request.getAge()!=null && request.getAge()<0){
//            return new LoginResponse(false,"age is invalid",request.getUsername(),null);
//        }
//
//        if(request.getUsername()==null || request.getUsername().isBlank()){
//            return new LoginResponse(false, "username is empty", null, null);
//        }
//        if(request.getPassword()==null || request.getPassword().isBlank()){
//            return new LoginResponse(false,"password is empty",request.getUsername(),null);
//        }
//        return new LoginResponse(true,"login request ok",request.getUsername(), request.getAge());
//    }
//
//    public LoginResponse mock(String name){
//        return new LoginResponse(true,"mock is ok",name,888);
//    }
//
//    public LoginResponse registerMock(){
//        return new LoginResponse(true,"register mock ok","new-user",20);
//    }
//
//
//}
