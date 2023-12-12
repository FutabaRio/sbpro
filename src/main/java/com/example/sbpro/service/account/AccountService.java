package com.example.sbpro.service.account;

import com.example.sbpro.controller.request.account.LoginDTO;
import com.example.sbpro.controller.response.ResultException;
import com.example.sbpro.controller.response.user.LoginRsp;
import com.example.sbpro.repository.AccountRepository;
import com.example.sbpro.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    public LoginRsp Login(LoginDTO loginDTO) {
        var account = accountRepository.findByEmail(loginDTO.getEmail(), loginDTO.getPassword());
        if (account == null) {
            throw new ResultException(401, "用户不存在或者密码错误");
        }
        var authToken = jwtService.issueAuthToken(account.getUser_id(), null);
        return new LoginRsp(authToken.getValue(), authToken.getExpiresAt());
    }
}