package com.example.sbpro.controller.request.users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank(message = "email 不能为空")
    String email;
    @NotBlank(message = "username 不能为空")
    String username;
    @NotBlank(message = "password 不能唯恐")
    @Min(6)
    String password;
}
