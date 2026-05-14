package com.hs.sbmergefakecrud.controller;

import com.hs.sbmergefakecrud.request.UserCreateRequest;
import com.hs.sbmergefakecrud.request.UserUpdateRequest;
import com.hs.sbmergefakecrud.response.Result;
import com.hs.sbmergefakecrud.response.UserResponse;
import com.hs.sbmergefakecrud.service.UserService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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

    @GetMapping("/users/{id}")
    public Result<UserResponse> findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping("/users/{id}")
    public Result<UserResponse> updateById(@PathVariable Long id,
                                           @RequestBody UserUpdateRequest request) {
        return userService.updateById(id,request);
    }

    @DeleteMapping("/users/{id}")
    public Result<UserResponse> deleteById(@PathVariable Long id){
        return userService.deleteById(id);
    }

    @GetMapping("/users/search")
    public Result<List<UserResponse>> search
            (@RequestParam(required = false) String keyword
                    ,@RequestParam(required = false)Integer minAge
                    ,@RequestParam(required = false)Integer maxAge){
        return userService.search(keyword,minAge,maxAge);
    }
}
