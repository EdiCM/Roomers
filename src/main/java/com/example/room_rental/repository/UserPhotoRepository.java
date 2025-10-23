package com.example.room_rental.repository;

import com.example.room_rental.model.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, UUID> {
    // Métodos básicos heredados de JpaRepository
}
