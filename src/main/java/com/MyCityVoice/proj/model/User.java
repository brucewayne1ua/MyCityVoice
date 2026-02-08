package com.MyCityVoice.proj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно")
    private String name;

    @Email(message = "Email должен быть корректным")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Пароль обязателен")
    private String password;

    // Can add roles, time of created etc
    private String role = "USER";

    // Constructors
    public User() {}  // need for JPA

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

