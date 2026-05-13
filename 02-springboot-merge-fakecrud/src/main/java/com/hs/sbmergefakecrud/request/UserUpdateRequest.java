package com.hs.sbmergefakecrud.request;

public class UserUpdateRequest {
    private String username;
    private Integer age;

    public UserUpdateRequest(){}

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
