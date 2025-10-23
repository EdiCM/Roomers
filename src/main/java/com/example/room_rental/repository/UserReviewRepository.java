package com.example.room_rental.repository;

import com.example.room_rental.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, UUID> {
    // Métodos básicos heredados de JpaRepository
}
