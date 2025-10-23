package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "property_review",
        // Constraint única: Un usuario solo puede reviewear una propiedad UNA vez
        // Esto evita duplicados sin necesidad de llave primaria compuesta
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"reviewer_property_id", "reviewed_property_id"}
        )
)
@Setter
@Getter
public class PropertyReview {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "property_review_id")
    private UUID propertyReviewId;

    // Relación Many-to-One con User (quien hace la review)
    @ManyToOne
    @JoinColumn(name = "reviewer_property_id", nullable = false)
    private User reviewerProperty;

    // Relación Many-to-One con PostProperty (propiedad que recibe la review)
    @ManyToOne
    @JoinColumn(name = "reviewed_property_id", nullable = false)
    private PostProperty reviewedProperty;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "comment", nullable = false)
    private String comment;

    // Constructor vacío (requerido por JPA)
    public PropertyReview() {
    }

    // Constructor con todos los campos obligatorios
    public PropertyReview(User reviewerProperty, PostProperty reviewedProperty, int score, String comment) {
        this.reviewerProperty = reviewerProperty;
        this.reviewedProperty = reviewedProperty;
        this.score = score;
        this.comment = comment;
    }
}
