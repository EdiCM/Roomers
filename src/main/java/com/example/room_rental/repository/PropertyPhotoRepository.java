package com.example.room_rental.repository;

import com.example.room_rental.model.PropertyPhoto;
import com.example.room_rental.model.PostProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, UUID> {

    // Buscar por property_id directamente todas las fotos de una propiedad
    List<PropertyPhoto> findByProperty_PropertyId(UUID propertyId);
}
