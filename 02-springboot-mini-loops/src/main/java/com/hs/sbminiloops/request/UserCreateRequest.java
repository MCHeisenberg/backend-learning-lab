package com.hs.sbminiloops.request;

public class UserCreateRequest {

    private String username;
    private Integer age;

    public UserCreateRequest(){}

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age=age;
    }

}
