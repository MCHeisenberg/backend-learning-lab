package com.hs.sbminiloops.service;

import com.hs.sbminiloops.entity.UserEntity;
import com.hs.sbminiloops.mapper.UserMapper;
import com.hs.sbminiloops.response.Result;
import com.hs.sbminiloops.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbUserService {
    private final UserMapper userMapper;

    public DbUserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public Result<List<UserResponse>> findAll(){
        List<UserEntity> entities=userMapper.findAll();
        List<UserResponse> responses=new ArrayList<>();
        for(UserEntity entity:entities){
            UserResponse response=new UserResponse(
                    entity.getId(),
                    entity.getUsername(),
                    entity.getAge()
            );
            responses.add(response);
        }
        return Result.success("query db user list ok",responses);
    }
}
