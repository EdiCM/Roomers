package com.example.room_rental.repository;

import com.example.room_rental.model.PropertyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyReviewRepository extends JpaRepository<PropertyReview, UUID> {
    // Métodos básicos heredados de JpaRepository
}
