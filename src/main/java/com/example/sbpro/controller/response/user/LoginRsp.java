package com.example.sbpro.controller.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoginRsp {
    private String Token;
    private LocalDateTime Exp;
    private UserInfo userInfo;
}


