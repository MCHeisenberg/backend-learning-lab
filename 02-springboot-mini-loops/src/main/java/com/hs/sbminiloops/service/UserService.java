package com.hs.sbminiloops.service;

import com.hs.sbminiloops.request.UserCreateRequest;
import com.hs.sbminiloops.request.UserUpdateRequest;
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
    //P2B2变式4 新增一个 helper method 把查找用户的 for 循环抽出来[helper method 减少重复逻辑。]
    private UserResponse findUserOrNull(Long id){
        for(UserResponse user:users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    //P2B2 变式 5：把 id 校验也抽成 helper[把重复判断压成更薄的辅助方法。]
    private boolean isInvalidId(Long id){
        return id==null || id<=0;
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

    private Result<UserResponse> checkUserExists(Long id){
        if(isInvalidId(id))
            return Result.fail("id is invalid");
        UserResponse user = findUserOrNull(id);
        if(user==null)
            return Result.fail("user not found");
        return Result.success("user exists",user);
    }

    public Result<UserResponse> create(UserCreateRequest request){
        if(request == null)
            return Result.fail("request is empty");

        if(request.getUsername() == null || request.getUsername().isBlank())
            return Result.fail("username is empty");

        //p2b1变式1：新增用户时校验 age 不能为空
        if(request.getAge()==null)
            return Result.fail("age is empty");

        if(request.getAge()<0)
            return Result.fail("age is invalid");

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
        UserResponse user = buildUser("mock-user"+nextId,18);
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

    //P2B2 新增方法
    public Result<UserResponse> findById(Long id){
        if(isInvalidId(id))
            return Result.fail("id is invalid");

        UserResponse user=findUserOrNull(id);
        if(user==null)
            return Result.fail("user not found");
        return Result.success("query user ok",user);
    }

    //P2B2变式 2：新增 exists 接口[Result<T> 的 T 可以是 Boolean。]
    public Result<Boolean> exists(Long id){
        if(isInvalidId(id))
            return Result.fail("id is invalid");
        if(findUserOrNull(id)==null)
            return Result.success("query user exists ok",false);
        return Result.success("query user exists ok",true);
    }

    //P2B2变式3：新增查询用户名接口[复用 findById 方法，不重复写遍历逻辑。]
    public Result<String> findNameById(Long id) {
        Result<UserResponse> result=findById(id);
        if(result.getSuccess())
            return Result.success("query name by id ok",result.getData().getUsername());
        return Result.fail(result.getMsg());
    }

    public Result<UserResponse> latest(){
        if(users.isEmpty())
            return Result.fail("user list is empty");
        UserResponse user=users.get(users.size()-1);
        return Result.success("query latest user ok",user);
    }

    public Result<UserResponse> first(){
        if(users.isEmpty())
            return Result.fail("user list is empty");
        return Result.success("query first user ok", users.get(0));
    }

    public Result<String> summary(Long id){
        Result<UserResponse> result = findById(id);
        if(!result.getSuccess())
            return Result.fail(result.getMsg());
        UserResponse user = result.getData();
        String text="id="+user.getId()+",username="
                +user.getUsername()+",age="+user.getAge();
        return Result.success("query user summary ok",text);
    }

//    public Result<UserResponse> deleteById(Long id){
//        if(isInvalidId(id))
//            return Result.fail("id is invalid");
//
//        int index = findIndexById(id);
//        if(index==-1)
//            return Result.fail("user not found");
//        UserResponse removeUser=users.remove(index);
//        return Result.success("delete user ok",removeUser);
//    }

    public Result<Integer> deleteByIdAndCount(Long id){
        Result<UserResponse> result=deleteById(id);
        if(!result.getSuccess())
            return Result.fail(result.getMsg());
        return Result.success("delete user and query count ok",users.size());
    }

    public Result<UserResponse> deleteLatest(){
        if(users.isEmpty())
            return Result.fail("user list is empty");
        UserResponse removeUser = users.remove(users.size()-1);
        return Result.success("delete latest user ok",removeUser);
    }

    public Result<UserResponse> deleteById(Long id){
        if(isInvalidId(id))
            return Result.fail("id is invalid");
        UserResponse removeUser = removeUserOrNull(id);
        if(removeUser==null)
            return Result.fail("user not found");
        return Result.success("delete user ok",removeUser);
    }

    public Result<Integer> clear(){
        int count=users.size();
        users.clear();
        return Result.success("clear users ok",count);
    }

    public Result<UserResponse> deleteFirst(){
        if(users.isEmpty())
            return Result.fail("user list is empty");
        UserResponse removedUser=users.remove(0);
        return Result.success("delete first user ok",removedUser);
    }

    public Result<UserResponse> updateById(Long id, UserUpdateRequest request){
        if(isInvalidId(id))
            return Result.fail("id is invalid");
        if(request==null)
            return Result.fail("request is empty");
        if(request.getUsername()==null||request.getUsername().isBlank())
            return Result.fail("username is empty");
        if(request.getAge()==null)
            return Result.fail("age is empty");
        if(request.getAge()<0)
            return Result.fail("age is invalid");

        UserResponse user=findUserOrNull(id);

        if(user==null)
            return Result.fail("user not found");

        user.setUsername(request.getUsername().trim());
        user.setAge(request.getAge());

        return Result.success("update user ok",user);
    }

    public Result<UserResponse> updateName(Long id,UserUpdateRequest request){
//        if(isInvalidId(id))
//            return Result.fail("id is invalid");
        if(request==null)
            return Result.fail("request is empty");
        if(request.getUsername()==null||request.getUsername().isBlank())
            return Result.fail("username is empty");

        Result<UserResponse> result=checkUserExists(id);
        if(!result.getSuccess())
            return Result.fail(result.getMsg());
        UserResponse user=result.getData();

        user.setUsername(request.getUsername().trim());
        return Result.success("update username ok",user);
    }

    public Result<UserResponse> updateAge(Long id,UserUpdateRequest request){
//        if(isInvalidId(id))
//            return Result.fail("id is invalid");
        if(request==null)
            return Result.fail("request is empty");
        if(request.getAge()==null)
            return Result.fail("age is empty");
        if(request.getAge()<0)
            return Result.fail("age is invalid");

        Result<UserResponse> result=checkUserExists(id);
        if(!result.getSuccess())
            return Result.fail(result.getMsg());
        UserResponse user=result.getData();

        user.setAge(request.getAge());
        return Result.success("update age ok",user);
    }

    public Result<String> updateAndSummary(Long id, UserUpdateRequest request){
        Result<UserResponse> result=updateById(id,request);
        if(!result.getSuccess())
            return Result.fail(result.getMsg());
        UserResponse user=result.getData();
        String text="id="+user.getId()+",username="+user.getUsername()
                +",age="+user.getAge();
        return Result.success("update user and query summary ok",text);
    }

    public Result<List<UserResponse>> updateAndList(Long id,UserUpdateRequest request){
        Result<UserResponse> result=updateById(id,request);
        if(!result.getSuccess())
            return Result.fail(result.getMsg());
        return Result.success("update user and query list ok",
                new ArrayList<>(users));
    }

}
