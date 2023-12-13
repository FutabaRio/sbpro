package com.example.sbpro.repository.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "roles")
@Data

@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue
    private int id;
    private int role_id;
    private String role;

}
