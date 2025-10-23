package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_data")
@Setter
@Getter
public class UserData {

    @Id
    @Column(name = "user_data_id")
    private UUID userId;

    // Relación One-to-One con User
    @OneToOne
    @MapsId  // ← Usa el mismo ID que User
    @JoinColumn(name = "user_data_id")
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age_range", nullable = false)
    private String ageRange;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "about_me", nullable = false)
    private String aboutMe;

    @Column(name = "has_pet")
    private boolean hasPet;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "is_smoker")
    private boolean isSmoker;

    // Constructor vacío (requerido por JPA)
    public UserData() {
    }
    // Constructor con parámetros
    // Constructor solo con campos obligatorios
    public UserData(User user, String name, String ageRange, String gender, String aboutMe) {
        this.user = user;
        this.name = name;
        this.ageRange = ageRange;
        this.gender = gender;
        this.aboutMe = aboutMe;
        this.hasPet = false;  // valor por defecto
        this.isSmoker = false;  // valor por defecto
    }

}
