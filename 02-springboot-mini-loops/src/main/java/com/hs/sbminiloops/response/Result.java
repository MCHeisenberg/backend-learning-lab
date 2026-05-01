package com.hs.sbminiloops.response;

public class Result<T> {
    private Boolean success;
    private String msg;
    private T data;

    //b6 变式 4：新增 `code` 字段
    private Integer code;

    public Result(){}

    public Result(Boolean success,String msg,T data,Integer code){
        this.success=success;
        this.msg=msg;
        this.data=data;
        this.code=code;
    }

    //b6 变式3 给 `Result` 加静态工厂方法。（减少Service里重复 `new Result<>(...)`）
    public static <T> Result<T> success(String msg,T data){
        return new Result<>(true, msg, data,200);
    }
    public static <T> Result<T> fail(String msg){
        return new Result<>(false,msg,null,400);
    }


    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
    public void setMsg(String msg){
        this.msg=msg;
    }

    public T getData(){
        return data;
    }
    public void setData(T data){
        this.data=data;
    }



}
