package com.hs.sbminiloopscrud.service;

import com.hs.sbminiloopscrud.entity.UserEntity;
import com.hs.sbminiloopscrud.mapper.UserMapper;
import com.hs.sbminiloopscrud.response.Result;
import com.hs.sbminiloopscrud.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbUserService {
    private final UserMapper userMapper;

    private List<UserResponse> toResponse(List<UserEntity> entities){
        List<UserResponse> responses = new ArrayList<>();
        for(UserEntity entity:entities){
            UserResponse response=new UserResponse(
                    entity.getId(),
                    entity.getUsername(), entity.getAge()
            );
            responses.add(response);
        }
        return responses;
    }

    public DbUserService (UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public Result<List<UserResponse>> findAll(){
        List<UserEntity> entities = userMapper.findAll();
        List<UserResponse> responses=toResponse(entities);
        return Result.success("query db user list ok",responses);
    }

    public Result<List<UserEntity>> findAllRaw() {
        return Result.success("query db user raw list ok",userMapper.findAll());
    }

    public Result<List<UserResponse>> findAllOrderByAgeDesc(){
        List<UserEntity> entities = userMapper.findAllOrderByAgeDesc();
        List<UserResponse> responses = toResponse(entities);
        return Result.success("query db user list order by age desc ok",responses);
    }

    public Result<Integer> count(){
        return Result.success("query db user count ok",userMapper.count());
    }



}
