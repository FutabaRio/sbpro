package com.example.sbpro.repository.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "permissions")
@Data
@NoArgsConstructor
public class Permissions {
    @Id
    @GeneratedValue
    private int id;
    private String permission;
    private String description;
}



