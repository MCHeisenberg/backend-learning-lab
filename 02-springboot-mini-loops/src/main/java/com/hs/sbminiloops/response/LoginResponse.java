package com.hs.sbminiloops.response;

public class LoginResponse {
    private String msg;
    private String username;
    private Integer age;
    private Boolean success;

    public LoginResponse(){}

    public LoginResponse(Boolean success, String message, String username, Integer age){
        this.success=success;
        this.msg=message;
        this.username=username;
        this.age=age;
    }
    public Boolean getSuccess(){
        return success;
    }
    public void setSuccess(Boolean success){
        this.success=success;
    }

    public String getMsg(){
        return msg;
    }
    public void setMessage(String message){
        this.msg=message;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String userName){
        this.username=username;
    }

    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age=age;
    }
}
