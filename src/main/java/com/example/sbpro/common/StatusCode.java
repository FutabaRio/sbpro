package com.example.sbpro.common;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusCode {
    USER_NOT_FOUND(10001, "Cant find User");


    public final int code;
    public final String message;
}
