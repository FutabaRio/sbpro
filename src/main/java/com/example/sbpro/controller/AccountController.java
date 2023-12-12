package com.example.sbpro.controller;

import com.example.sbpro.config.external.SbCommonProperties;
import com.example.sbpro.controller.request.account.LoginDTO;
import com.example.sbpro.controller.response.Result;
import com.example.sbpro.controller.response.user.LoginRsp;
import com.example.sbpro.service.account.AccountService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Data
public class AccountController {
    private final AccountService accountService;
    private final SbCommonProperties sbCommonProperties;

    @PostMapping("/login")
    public Result<LoginRsp> login(@RequestBody LoginDTO loginDTO) {
        return Result.success("登录成功", accountService.Login(loginDTO));
    }
}



