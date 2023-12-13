package com.example.sbpro.controller.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String email;
    private String userId;
    private String username;
}
