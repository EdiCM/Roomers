package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "post_coliving")
@Setter
@Getter
public class PostColiving {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "coliving_id")
    private UUID colivingId;

    // Relación Many-to-One con User (quien hace la publicación)
    @ManyToOne
    @JoinColumn(name = "coliving_owner_id", nullable = false)
    private User colivingOwner;

    // Relación Many-to-One con PostProperty (muchos posts pueden referenciar la misma propiedad)
    @ManyToOne
    @JoinColumn(name = "posted_property_id", nullable = false)
    private PostProperty postedProperty;

    @Column(name = "description", nullable = false)
    private String description;

    // Constructor vacío (requerido por JPA)
    public PostColiving() {
    }

    // Constructor con todos los campos obligatorios
    public PostColiving(User colivingOwner, PostProperty postedProperty, String description) {
        this.colivingOwner = colivingOwner;
        this.postedProperty = postedProperty;
        this.description = description;
    }
}
