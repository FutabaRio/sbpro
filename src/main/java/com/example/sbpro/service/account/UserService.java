package com.example.sbpro.service.account;

import com.example.sbpro.controller.request.users.LoginDTO;
import com.example.sbpro.controller.request.users.RegisterDTO;
import com.example.sbpro.controller.request.users.UserInfoUpdateDTO;
import com.example.sbpro.controller.response.ResultException;
import com.example.sbpro.controller.response.user.LoginRsp;
import com.example.sbpro.controller.response.user.UserInfo;
import com.example.sbpro.repository.UserRepository;
import com.example.sbpro.repository.entity.User.Users;
import com.example.sbpro.service.jwt.JwtService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginRsp Login(LoginDTO loginDTO) {
        var user = userRepository.findByEmail(loginDTO.getEmail());
        if (user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ResultException(401, "用户不存在或者密码错误");
        }
        var authToken = jwtService.issueAuthToken(user.getUserId(), null);
        return new LoginRsp(authToken.getValue(), authToken.getExpiresAt(), new UserInfo(
                user.getEmail(), user.getUserId(), user.getUsername()
        ));
    }

    public UserInfo Register(RegisterDTO registerDTO) {
        String encode = passwordEncoder.encode(registerDTO.getPassword());
        Users user = new Users();
        user.setUserId(String.valueOf(UUID.randomUUID()));
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(encode);
        user.setUser_role("0");
        UserInfo userinfo;
        try {
            var save = userRepository.save(user);
            userinfo = new UserInfo(save.getEmail(), save.getUserId(), save.getUsername());
        } catch (DataAccessException e) {
            throw new ResultException(20001, "用户已存在");
        }
        return userinfo;
    }


    public UserInfo GetUserInfo(String email) {
        var user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResultException(20002, "用户不存在");
        }
        return new UserInfo(user.getEmail(), user.getUserId(), user.getUsername());
    }

    public UserInfo UpdateUserInfo(@NotNull String email, UserInfoUpdateDTO userInfoUpdateDTO) {
        var user = userRepository.findByEmail(email);
        user.setUsername(userInfoUpdateDTO.getUsername());
        user.setPassword(userInfoUpdateDTO.getPassword());
        userRepository.save(user);
        return null;
    }
}