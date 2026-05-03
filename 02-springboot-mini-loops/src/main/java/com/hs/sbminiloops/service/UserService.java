package com.hs.sbminiloops.service;

import com.hs.sbminiloops.request.UserCreateRequest;
import com.hs.sbminiloops.response.Result;
import com.hs.sbminiloops.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<UserResponse> users = new ArrayList<>();
    private long nextId = 1L;

    public Result<UserResponse> create(UserCreateRequest request){
        if(request == null){
            return Result.fail("request is empty");
        }

        if(request.getUsername() == null || request.getUsername().isBlank()){
            return Result.fail("username is empty");
        }

        if(request.getAge()!=null && request.getAge()<0){
            return Result.fail("age is invalid");
        }

        UserResponse user = new UserResponse(nextId, request.getUsername()
                , request.getAge());
        nextId++;
        users.add(user);

        return Result.success("create user ok",user);
    }

    public Result<List<UserResponse>> list(){
        return Result.success("query user list ok",users);
    }

}
