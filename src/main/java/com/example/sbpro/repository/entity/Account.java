package com.example.sbpro.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "account")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private int id;
    private String user_id;
    private String email;
    private int phone;
}
