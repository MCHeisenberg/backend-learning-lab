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

    private int findIndexById(Long id){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }

    private UserResponse removeUserOrNull(Long id){
        int index=findIndexById(id);
        if(index==-1)
            return null;
        return users.remove(index);
    }

    private boolean matchUser(UserResponse user,String keyword,Integer minAge,Integer maxAge){
        if(keyword!=null && !keyword.isBlank()){
            String cleanedKeyword=keyword.trim();
            if(user.getUsername()==null||!user.getUsername().contains(cleanedKeyword))
                return false;
        }
        if(minAge!=null){
            if(user.getAge()==null||user.getAge()<minAge)
                return false;
        }
        if(maxAge!=null) {
            if (user.getAge() == null || user.getAge() > maxAge)
                return false;
        }
        return true;
    }

    private Result<Void> validateAgeRange(Integer minAge,Integer maxAge){
        if(minAge!=null&&minAge<0)
            return Result.fail("minAge is invalid");
        if(maxAge!=null&&maxAge<0)
            return Result.fail("maxAge is invalid");
        if(minAge!=null&&maxAge!=null&&minAge>maxAge)
            return Result.fail("age range is invalid");
        return Result.success("age range ok",null);
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

    public Result<UserResponse> deleteById(Long id){
        if(isInvalidId(id))
            return Result.fail("id is invalid");
        UserResponse removeUser = removeUserOrNull(id);
        if(removeUser==null)
            return Result.fail("user not found");
        return Result.success("delete user ok",removeUser);
    }

    public Result<List<UserResponse>> search(String keyword,Integer minAge,Integer maxAge){
        Result<Void> validateResult = validateAgeRange(minAge,maxAge);
        if(!validateResult.getSuccess())
            return Result.fail(validateResult.getMsg());

        List<UserResponse> result=new ArrayList<>();

        for(UserResponse user:users){
            if(matchUser(user,keyword,minAge,maxAge))
                result.add(user);
        }
        return Result.success("search user ok",result);
    }
}
