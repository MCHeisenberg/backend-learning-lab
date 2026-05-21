package com.hs.sbminiloopscrud.service;

import com.hs.sbminiloopscrud.entity.UserEntity;
import com.hs.sbminiloopscrud.mapper.UserMapperPrac;
import com.hs.sbminiloopscrud.response.Result;
import com.hs.sbminiloopscrud.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class DbUserServicePrac {

    private final UserMapperPrac userMapperPrac;

    public DbUserServicePrac(UserMapperPrac userMapperPrac) {
        this.userMapperPrac = userMapperPrac;
    }

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

    public Result<List<UserResponse>> findAllOrderByUsernameAsc(){
        List<UserEntity> entities = userMapperPrac.findAllOrderByUsernameAsc();
        List<UserResponse> responses=toResponse(entities);
        return Result.success("query db user OrderByUsername ok",responses);
    }
}
