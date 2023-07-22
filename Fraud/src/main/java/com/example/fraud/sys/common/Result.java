package com.example.fraud.sys.common;

public class Result {
    private Object data;

    public Result(Object data) {
        this.data = data;
    }
    public void Getdata(Object data){
        this.data=data;
    }
    public Object Setdata(Object data){
        return data;
    }
    public static Result success(Object data){
        return new Result(data);
    }
    public static Result success(){
        return  new Result(null);
    }
}
