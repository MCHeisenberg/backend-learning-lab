package com.hs.sbmergefakecrud.service;

import com.hs.sbmergefakecrud.request.UserCreateRequest;
import com.hs.sbmergefakecrud.request.UserUpdateRequest;
import com.hs.sbmergefakecrud.response.Result;
import com.hs.sbmergefakecrud.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<UserResponse> users=new ArrayList<>();
    private Long nextId=1L;
    private UserResponse buildUser(String username,Integer age){
        UserResponse user = new UserResponse(nextId,username,age);
        nextId++;
        users.add(user);
        return user;
    }
    private boolean isInvalidId(Long id){
        return id==null||id<=0;
    }
    private UserResponse findUserOrNull(Long id){
        for(UserResponse user:users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    private Result<UserResponse> checkUserExists(Long id){
        if(isInvalidId(id))
            return Result.fail("id is invalid");
        UserResponse user = findUserOrNull(id);
        if(user==null)
            return Result.fail("user not found");
        return Result.success("user exists",user);
    }

    public Result<UserResponse> create(UserCreateRequest request) {
        if(request == null)
            return Result.fail("request is empty");

        if(request.getUsername() == null || request.getUsername().isBlank())
            return Result.fail("username is empty");

        //p2b1变式1：新增用户时校验 age 不能为空
        if(request.getAge()==null)
            return Result.fail("age is empty");

        if(request.getAge()<0)
            return Result.fail("age is invalid");

        String username= request.getUsername().trim();
        UserResponse user=buildUser(username, request.getAge());

        return Result.success("create user ok",user);
    }

    public Result<List<UserResponse>> list() {
        return Result.success("query user list ok",new ArrayList(users));
    }

    public Result<UserResponse> findById(Long id) {
        if(isInvalidId(id))
            return Result.fail("id is invalid");
        UserResponse user=findUserOrNull(id);
        if(user==null)
            return Result.fail("user not found");
        return Result.success("query user ok",user);
    }


    public Result<UserResponse> updateById(Long id, UserUpdateRequest request) {
        Result<UserResponse> result=checkUserExists(id);
        if(!result.getSuccess())
            return Result.fail(result.getMsg());

        if(request==null)
            return Result.fail("request is empty");
        if(request.getUsername()==null||request.getUsername().isBlank())
            return Result.fail("username is empty");
        if(request.getAge()==null)
            return Result.fail("age is empty");
        if(request.getAge()<0)
            return Result.fail("age is invalid");

        UserResponse user=result.getData();

        user.setUsername(request.getUsername().trim());
        user.setAge(request.getAge());

        return Result.success("update user ok",user);

    }
}
