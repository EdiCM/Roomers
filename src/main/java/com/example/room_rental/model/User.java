package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity// Entity "table" type, named user
@Table(name = "users")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // Constructor vacío (requerido por JPA)
    public User() {
    }

    // Constructor con parámetros
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
