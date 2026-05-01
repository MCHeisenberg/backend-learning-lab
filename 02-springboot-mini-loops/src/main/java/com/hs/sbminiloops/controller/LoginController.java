package com.hs.sbminiloops.controller;

import com.hs.sbminiloops.request.LoginRequest;
import com.hs.sbminiloops.response.LoginResponse;
import com.hs.sbminiloops.response.Result;
import com.hs.sbminiloops.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController{
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }

    //block06 变式2：新增一个失败版 mock。(失败时 `data` 可以是 `null`，但外层结构仍然统一。)
    @PostMapping("/auth/mock-fail")
    public Result<LoginResponse> mockFail(){
        return loginService.mockFail();
    }

    @PostMapping("/auth/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request){
        return loginService.login(request);
    }

    //Controller 里故意保留一点点逻辑再挪走；让两个接口复用一个Service方法
    @PostMapping("/auth/mock-login")
    public Result<LoginResponse> mockLogin(@RequestBody LoginRequest request){
//        if(request == null){
//            return new LoginResponse(false,"request is empty",null,null);
//        }
        return loginService.login(request);
    }

    @PostMapping("/auth/mock")
    public Result<LoginResponse> mock(@RequestParam(required = false) String name){
//b6 变式 5：让 `/auth/mock` 缺 name 时返回统一失败,b6 加强 A：把校验逻辑继续放回 Service
//        if(name==null || name.isBlank()){
//            return Result.fail("name is empty");
//        }
        return loginService.mock(name);
    }

    @PostMapping("/auth/register-mock")
    public Result<LoginResponse> registerMock(){
        return loginService.registerMock();
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
