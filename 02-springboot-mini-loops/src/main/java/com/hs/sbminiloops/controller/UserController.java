package com.hs.sbminiloops.controller;

import com.hs.sbminiloops.request.UserCreateRequest;
import com.hs.sbminiloops.response.Result;
import com.hs.sbminiloops.response.UserResponse;
import com.hs.sbminiloops.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    //p2b1 变式 2：新增一个默认用户接口(练：不一定每次都从 @RequestBody 创建，也可以由 Service 内部生成数据。)
    @PostMapping("/users/mock")
    public Result<UserResponse> createMock(){
        return userService.createMock();
    }

    //p2b1 变式 4：新增用户后返回列表[练：Result<T> 的 T 可以从 UserResponse 换成 List<UserResponse>。]
    @PostMapping("/users/mock-and-list")
    public Result<List<UserResponse>> createMockAndList(){
        return userService.createMockAndList();
    }

    //p2b1变式4：新增用户后返回列表[练：Result<T> 的 T 可以从 UserResponse 换成 List<UserResponse>。]
    @GetMapping("/users/count")
    public Result<Integer> count(){
        return userService.count();
    }

    //P2B2 新增接口
    @GetMapping("/users/{id}")
    public Result<UserResponse> findById(@PathVariable Long id){
        return userService.findById(id);
    }

    //P2B2变式 2：新增 exists 接口
    @GetMapping("/users/{id}/exists")
    public Result<Boolean> exists(@PathVariable Long id){
        return userService.exists(id);
    }

    //P2B2变式3：新增查询用户名接口
    @GetMapping("/users/{id}/name")
    public Result<String> findNameById(@PathVariable Long id){
        return userService.findNameById(id);
    }

    @GetMapping("/users/latest")
    public Result<UserResponse> latest(){
        return userService.latest();
    }

    @GetMapping("/users/first")
    public Result<UserResponse> first(){
        return userService.first();
    }

    @GetMapping("/users/{id}/summary")
    public Result<String> summary(@PathVariable Long id){
        return userService.summary(id);
    }

}
