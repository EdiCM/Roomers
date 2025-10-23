package com.example.room_rental.controller;

import com.example.room_rental.model.PropertyReview;
import com.example.room_rental.model.User;
import com.example.room_rental.model.PostProperty;
import com.example.room_rental.repository.PropertyReviewRepository;
import com.example.room_rental.repository.UserRepository;
import com.example.room_rental.repository.PostPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/property-review")
public class PropertyReviewController {

    @Autowired
    private PropertyReviewRepository propertyReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostPropertyRepository postPropertyRepository;

    // CREAR PROPERTY REVIEW
    @PostMapping("/{reviewerId}/{propertyId}")
    public PropertyReview createPropertyReview(
            @PathVariable UUID reviewerId,
            @PathVariable UUID propertyId,
            @RequestBody PropertyReview reviewRequest) {

        // Buscar el usuario que hace la review
        User reviewer = userRepository.findById(reviewerId).orElse(null);
        if (reviewer == null) {
            return null;
        }

        // Buscar la propiedad que recibe la review
        PostProperty property = postPropertyRepository.findById(propertyId).orElse(null);
        if (property == null) {
            return null;
        }

        // Validación: Score debe estar entre 1 y 5
        if (reviewRequest.getScore() < 1 || reviewRequest.getScore() > 5) {
            return null;  // O lanzar excepción
        }

        // Crear PropertyReview con todos los campos obligatorios
        PropertyReview propertyReview = new PropertyReview(
                reviewer,
                property,
                reviewRequest.getScore(),
                reviewRequest.getComment()
        );

        return propertyReviewRepository.save(propertyReview);
    }

    // DEVUELVEME TODAS LAS PROPERTY REVIEWS
    @GetMapping
    public List<PropertyReview> getAllPropertyReviews() {
        return propertyReviewRepository.findAll();
    }

    // DEVUELVEME PROPERTY REVIEW POR ID
    @GetMapping("/{id}")
    public PropertyReview getPropertyReviewById(@PathVariable UUID id) {
        return propertyReviewRepository.findById(id).orElse(null);
    }

    // ACTUALIZA PROPERTY REVIEW POR ID
    @PutMapping("/{id}")
    public PropertyReview updatePropertyReview(@PathVariable UUID id, @RequestBody PropertyReview updatedReview) {
        PropertyReview propertyReview = propertyReviewRepository.findById(id).orElse(null);
        if (propertyReview != null) {
            // Validación: Score debe estar entre 1 y 5
            if (updatedReview.getScore() < 1 || updatedReview.getScore() > 5) {
                return null;
            }

            // Actualizar score y comment (las relaciones no deberían cambiar)
            propertyReview.setScore(updatedReview.getScore());
            propertyReview.setComment(updatedReview.getComment());

            return propertyReviewRepository.save(propertyReview);
        }
        return null;
    }

    // ELIMINA PROPERTY REVIEW POR ID
    @DeleteMapping("/{id}")
    public void deletePropertyReview(@PathVariable UUID id) {
        propertyReviewRepository.deleteById(id);
    }
}
