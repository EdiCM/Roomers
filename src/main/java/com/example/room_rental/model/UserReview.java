package com.example.room_rental.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_review",
        // Constraint única: Un usuario solo puede reviewear a otro usuario UNA vez
        // Esto evita duplicados sin necesidad de llave primaria compuesta
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"reviewer_id", "reviewed_user_id"}
        )
)
@Setter
@Getter
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_review_id")
    private UUID userReviewId;

    // Relación Many-to-One con User (quien hace la review)
    @ManyToOne
    @JoinColumn(name = "reviewer_user_id", nullable = false)
    private User reviewerUser;

    // Relación Many-to-One con User (quien recibe la review)
    @ManyToOne
    @JoinColumn(name = "reviewed_user_id", nullable = false)
    private User reviewedUser;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "comment", nullable = false)
    private String comment;

    // Constructor vacío (requerido por JPA)
    public UserReview() {
    }

    // Constructor con todos los campos obligatorios
    public UserReview(User reviewerUser, User reviewedUser, int score, String comment) {
        this.reviewerUser = reviewerUser;
        this.reviewedUser = reviewedUser;
        this.score = score;
        this.comment = comment;
    }
}