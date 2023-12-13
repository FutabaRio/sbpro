package com.example.sbpro.repository.entity.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    private int id;
    @UuidGenerator
    private String userId;
    private String username;
    @Column(unique = true)
    @NotBlank(message = "邮箱为唯一身份标识，不能为空")
    private String email;
    private String password;
    private String user_role;
}


