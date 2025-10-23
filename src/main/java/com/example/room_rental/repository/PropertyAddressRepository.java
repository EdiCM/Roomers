package com.example.room_rental.repository;

import com.example.room_rental.model.PropertyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PropertyAddressRepository extends JpaRepository<PropertyAddress, UUID> {
}
