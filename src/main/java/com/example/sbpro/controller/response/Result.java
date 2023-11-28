package com.example.sbpro.controller.response;

import java.io.Serializable;

public record Result<T>(int code, String message, T data) implements Serializable {

    public static <T> Result<T> success(T data) {
        return success(null,data);
    }

    public static <T> Result<T> success() {
        return success(null,null);
    }
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    public static <T> Result<T> fail(int statusCode, String message, T data){
        return new Result<>(statusCode,message,data);
    }
    public static <T> Result<T> fail(int statusCode, String message){
        return fail(statusCode,message,null);
    }
}
