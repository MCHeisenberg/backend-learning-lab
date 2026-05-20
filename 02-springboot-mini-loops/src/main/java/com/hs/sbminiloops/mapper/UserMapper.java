package com.hs.sbminiloops.mapper;

import com.hs.sbminiloops.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("""
            SELECT id, username, age, create_at, updated_at
            FROM app_user
            ORDER BY id ASC
            """)
    List<UserEntity> findAll();
}
