package com.example.sbpro.controller.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message="邮件格式错误")
    private String email;
}
