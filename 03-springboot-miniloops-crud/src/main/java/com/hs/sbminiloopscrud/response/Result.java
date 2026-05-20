package com.hs.sbminiloopscrud.response;

public class Result<T> {
    private T data;
    private Boolean success;
    private Integer code;
    private String msg;

    public static <T> Result<T> success(String msg,T data){
        return new Result<>(data,true,200,msg);
    }
    public static <T> Result<T> fail(String msg){
        return new Result<>(null,false,400,msg);
    }

    public Result(){}

    public Result(T data,Boolean success,Integer code,String msg){
        this.code=code;
        this.data=data;
        this.success=success;
        this.msg=msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
