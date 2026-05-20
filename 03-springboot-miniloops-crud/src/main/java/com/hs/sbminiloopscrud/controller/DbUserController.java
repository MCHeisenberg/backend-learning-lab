package com.hs.sbminiloopscrud.controller;

import com.hs.sbminiloopscrud.entity.UserEntity;
import com.hs.sbminiloopscrud.response.Result;
import com.hs.sbminiloopscrud.response.UserResponse;
import com.hs.sbminiloopscrud.service.DbUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbUserController {
    private final DbUserService dbUserService;
    public DbUserController(DbUserService dbUserService){
        this.dbUserService=dbUserService;
    }

    @GetMapping("/db/users")
    public Result<List<UserResponse>> findAll(){
        return dbUserService.findAll();
    }

    @GetMapping("/db/users/raw")
    public Result<List<UserEntity>> findAllRaw(){
        return dbUserService.findAllRaw();
    }

    @GetMapping("/db/users/order-by-age")
    public Result<List<UserResponse>> findAllOrderByAgeDesc(){
        return dbUserService.findAllOrderByAgeDesc();
    }
}
