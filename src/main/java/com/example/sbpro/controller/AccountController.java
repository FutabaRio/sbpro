package com.example.sbpro.controller;

import com.example.sbpro.controller.request.user.LoginDTO;
import com.example.sbpro.controller.response.Result;
import com.example.sbpro.controller.response.user.LoginRsp;
import com.example.sbpro.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/login")
    public Result<LoginRsp> login(@Valid LoginDTO user) {

        return Result.success("登录成功", accountService.Login(user));
    }
}



