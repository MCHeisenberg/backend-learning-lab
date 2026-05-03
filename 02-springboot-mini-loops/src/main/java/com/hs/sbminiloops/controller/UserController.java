package com.hs.sbminiloops.controller;

import com.hs.sbminiloops.request.UserCreateRequest;
import com.hs.sbminiloops.response.Result;
import com.hs.sbminiloops.response.UserResponse;
import com.hs.sbminiloops.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/users")
    public Result<UserResponse> create(@RequestBody UserCreateRequest request){
        return userService.create(request);
    }

    @GetMapping("/users")
    public Result<List<UserResponse>> list(){
        return userService.list();
    }

}
