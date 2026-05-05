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
    private Long nextId = 1L;
    //P2B1加强A：抽一个 helper method[helper method = 类内部辅助方法，用来减少重复逻辑。]
    private UserResponse buildUser(String username,Integer age){
        UserResponse user = new UserResponse(nextId,username,age);
        nextId++;
        users.add(user);
        return user;
    }

    public Result<UserResponse> create(UserCreateRequest request){
        if(request == null){
            return Result.fail("request is empty");
        }

        if(request.getUsername() == null || request.getUsername().isBlank()){
            return Result.fail("username is empty");
        }

        //p2b1变式1：新增用户时校验 age 不能为空
        if(request.getAge()==null){
            return Result.fail("age is empty");
        }

        if(request.getAge()<0){
            return Result.fail("age is invalid");
        }

        //p2b1变式5：新增用户时 username 去空格[Service 不只是搬运参数，还可以做业务前处理。]
        String username= request.getUsername().trim();
        UserResponse user = buildUser(username, request.getAge());

        return Result.success("create user ok",user);
    }

    public Result<List<UserResponse>> list(){
        //P2B1加强B：让 list 返回副本[不要直接把内部集合暴露出去]
        return Result.success("query user list ok",new ArrayList<>(users));
    }

    //p2b1变式2：新增一个默认用户接口(练：不一定每次都从 @RequestBody 创建，也可以由 Service 内部生成数据。)
    public Result<UserResponse> createMock(){
        UserResponse user = buildUser("mock-user",18);
        return Result.success("create mock user ok",user);
    }

    //p2b1变式4：新增用户后返回列表[练：Result<T> 的 T 可以从 UserResponse 换成 List<UserResponse>。]
    public Result<List<UserResponse>> createMockAndList(){
        buildUser("mock-user-"+nextId,18);
        return Result.success("create mock and query list ok",new ArrayList<>(users));
    }

    //P2B1加强C：新增计数接口[Result<T> 的 T 还可以是 Integer。]
    public Result<Integer> count(){
        return Result.success("query user count ok", users.size());
    }

}
