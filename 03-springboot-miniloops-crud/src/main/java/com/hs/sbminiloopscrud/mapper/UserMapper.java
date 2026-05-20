package com.hs.sbminiloopscrud.mapper;

import com.hs.sbminiloopscrud.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("""
            SELECT id,username,age,created_at,updated_at
            FROM app_user
            ORDER BY id ASC
            """)
    List<UserEntity> findAll();

    @Select("""
            SELECT id,username,age,created_at,updated_at
            FROM app_user
            ORDER BY AGE DESC
            """)
    List<UserEntity> findAllOrderByAgeDesc();
}
