package com.example.sbpro.service;

import com.example.sbpro.controller.request.user.LoginDTO;
import com.example.sbpro.controller.response.ResultException;
import com.example.sbpro.controller.response.user.LoginRsp;
import com.example.sbpro.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public LoginRsp Login(LoginDTO loginDTO) {
        var email = accountRepository.findByEmail(loginDTO.getEmail());
        if (email == null) {
            throw new ResultException(401, "cant find email");
        }
        return new LoginRsp("123");
    }
}