package com.kishi.quiz_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true,nullable = false)
private String email;

@Column(nullable = false)
private String username;

@Column(nullable = false)
private String password;

public User(){

}

}
