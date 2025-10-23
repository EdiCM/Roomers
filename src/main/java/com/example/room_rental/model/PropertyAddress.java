package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "property_address")
@Setter
@Getter
public class PropertyAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "property_address_id")
    private UUID propertyAddressId;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "suburb", nullable = false)
    private String suburb;

    // Constructor vacío (requerido por JPA)
    public PropertyAddress() {
    }

    // Constructor con todos los campos (todos son obligatorios)
    public PropertyAddress(float latitude, float longitude, String country, String state, String city, String suburb) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.state = state;
        this.city = city;
        this.suburb = suburb;
    }
}
