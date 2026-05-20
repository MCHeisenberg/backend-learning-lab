package com.hs.sbminiloopscrud.response;

public class UserResponse {
    private Long id;
    private Integer age;
    private String username;

    public UserResponse(Long id, Integer age, String username) {
        this.id = id;
        this.age = age;
        this.username = username;
    }

    public UserResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
