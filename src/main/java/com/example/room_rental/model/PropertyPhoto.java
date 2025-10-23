package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "property_photo")
@Setter
@Getter
public class PropertyPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "photo_id")
    private UUID photoId;

    // Relación Many-to-One con PostProperty (muchas fotos por propiedad)
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private PostProperty property;

    @Lob  // ← Large Object para almacenar datos binarios grandes
    @Column(name = "photo_path", nullable = false)
    private byte[] photoPath;  // Almacena la foto en binario

    // Constructor vacío (requerido por JPA)
    public PropertyPhoto() {
    }

    // Constructor con campos obligatorios
    public PropertyPhoto(PostProperty property, byte[] photoPath) {
        this.property = property;
        this.photoPath = photoPath;
    }
}
