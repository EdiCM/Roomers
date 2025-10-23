package com.example.room_rental.controller;

import com.example.room_rental.model.User;
import com.example.room_rental.model.UserReview;
import com.example.room_rental.repository.UserRepository;
import com.example.room_rental.repository.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-review")
public class UserReviewController {

    @Autowired
    private UserReviewRepository userReviewRepository;

    @Autowired
    private UserRepository userRepository;

    // CREAR USER REVIEW
    @PostMapping("/{reviewerId}/{reviewedUserId}")
    public UserReview createUserReview(
            @PathVariable UUID reviewerId,
            @PathVariable UUID reviewedUserId,
            @RequestBody UserReview reviewRequest) {

        // Validación: Un usuario no puede reviewearse a sí mismo
        if (reviewerId.equals(reviewedUserId)) {
            return null;  // O lanzar excepción
        }

        // Buscar el usuario que hace la review
        User reviewer = userRepository.findById(reviewerId).orElse(null);
        if (reviewer == null) {
            return null;
        }

        // Buscar el usuario que recibe la review
        User reviewedUser = userRepository.findById(reviewedUserId).orElse(null);
        if (reviewedUser == null) {
            return null;
        }

        // Crear UserReview con todos los campos obligatorios
        UserReview userReview = new UserReview(
                reviewer,
                reviewedUser,
                reviewRequest.getScore(),
                reviewRequest.getComment()
        );

        return userReviewRepository.save(userReview);
    }

    // DEVUELVEME TODAS LAS REVIEWS
    @GetMapping
    public List<UserReview> getAllUserReviews() {
        return userReviewRepository.findAll();
    }

    // DEVUELVEME USER REVIEW POR ID
    @GetMapping("/{id}")
    public UserReview getUserReviewById(@PathVariable UUID id) {
        return userReviewRepository.findById(id).orElse(null);
    }

    // ACTUALIZA USER REVIEW POR ID
    @PutMapping("/{id}")
    public UserReview updateUserReview(@PathVariable UUID id, @RequestBody UserReview updatedReview) {
        UserReview userReview = userReviewRepository.findById(id).orElse(null);
        if (userReview != null) {
            // Validación: Score debe estar entre 1 y 5
            if (updatedReview.getScore() < 1 || updatedReview.getScore() > 5) {
                return null;
            }

            // Actualizar score y comment (las relaciones no deberían cambiar)
            userReview.setScore(updatedReview.getScore());
            userReview.setComment(updatedReview.getComment());

            return userReviewRepository.save(userReview);
        }
        return null;
    }

    // ELIMINA USER REVIEW POR ID
    @DeleteMapping("/{id}")
    public void deleteUserReview(@PathVariable UUID id) {
        userReviewRepository.deleteById(id);
    }
}
