package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_photo")
@Setter
@Getter
public class UserPhoto {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    // Relación One-to-One con User
    @OneToOne
    @MapsId  // ← Usa el mismo ID que User
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob  // ← Large Object para almacenar datos binarios grandes
    @Column(name = "photo_path", nullable = false)
    private byte[] photoPath;  // Almacena la foto en binario

    // Constructor vacío (requerido por JPA)
    public UserPhoto() {
    }

    // Constructor con campos obligatorios
    public UserPhoto(User user, byte[] photoPath) {
        this.user = user;
        this.photoPath = photoPath;
    }
}
