package com.example.ProductApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

}
