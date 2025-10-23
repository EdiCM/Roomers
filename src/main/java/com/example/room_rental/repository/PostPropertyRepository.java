package com.example.room_rental.repository;

import com.example.room_rental.model.PostProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostPropertyRepository extends JpaRepository<PostProperty, UUID> {
}

