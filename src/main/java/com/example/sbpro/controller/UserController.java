package com.example.sbpro.controller;

import com.example.sbpro.config.external.SbCommonProperties;
import com.example.sbpro.controller.request.users.LoginDTO;
import com.example.sbpro.controller.request.users.RegisterDTO;
import com.example.sbpro.controller.response.Result;
import com.example.sbpro.controller.response.user.LoginRsp;
import com.example.sbpro.controller.response.user.UserInfo;
import com.example.sbpro.service.account.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Data
public class UserController {
    private final UserService userService;
    private final SbCommonProperties sbCommonProperties;

    @PostMapping("/login")
    public Result<LoginRsp> login(@RequestBody LoginDTO loginDTO) {
        return Result.success("登录成功", userService.Login(loginDTO));
    }

    @PostMapping("/register")
    private Result<UserInfo> register(@RequestBody RegisterDTO registerDTO) {
        return Result.success("注册成功", userService.Register(registerDTO));
    }

    @GetMapping("/userinfo")
    private Result<UserInfo> userinfo(@Valid String email){
        return Result.success("成功", userService.GetUserInfo(email));
    }
}



