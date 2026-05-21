package com.hs.sbminiloopscrud.controller;

import com.hs.sbminiloopscrud.response.Result;
import com.hs.sbminiloopscrud.response.UserResponse;
import com.hs.sbminiloopscrud.service.DbUserService;
import com.hs.sbminiloopscrud.service.DbUserServicePrac;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbUserControllerPrac {
    private final DbUserServicePrac dbUserServicePrac;
    public DbUserControllerPrac(DbUserServicePrac dbUserServicePrac){
        this.dbUserServicePrac=dbUserServicePrac;
    }

    @GetMapping("/db/users/order-by-username")
    public Result<List<UserResponse>> findAllOrderByUsernameAsc(){
        return dbUserServicePrac.findAllOrderByUsernameAsc();
    }




}
