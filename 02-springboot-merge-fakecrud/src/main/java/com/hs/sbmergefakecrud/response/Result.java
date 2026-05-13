package com.hs.sbmergefakecrud.response;

public class Result<T> {
    private Boolean success;
    private String msg;
    private Integer code;
    private T data;

    public static <T> Result<T> success(String msg,T data){
        return new Result<T>(true,data,2000,msg);
    }

    public static <T> Result<T> fail(String msg){
        return new Result<T>(false,null,4000,msg);
    }

    public Result(Boolean success, T data, Integer code, String msg) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
