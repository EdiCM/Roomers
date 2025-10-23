package com.example.room_rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "post_property")
@Setter
@Getter
public class PostProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "property_id")
    private UUID propertyId;

    // Relación Many-to-One con User (propietario), un usuario tiene muchas propiedades
    @ManyToOne
    @JoinColumn(name = "property_owner_id", nullable = false) // El nullable dice, esta relacion es obligatoria
    private User propertyOwner;

    // Relación One-to-One con PropertyAddress (cada propiedad tiene UNA dirección única)
    @OneToOne
    @JoinColumn(name = "property_address_id", nullable = false)
    private PropertyAddress propertyAddress;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "time_agreement", nullable = false)
    private String timeAgreement;

    @Column(name = "rental_type", nullable = false)
    private String rentalType;

    @Column(name = "rooms_number", nullable = false)
    private int roomsNumber;

    @Column(name = "has_services", nullable = false)
    private boolean hasServices;

    @Column(name = "services_type", nullable = false)
    private String servicesType;

    @Column(name = "owner_lives_in_property", nullable = false)
    private boolean ownerLivesInProperty;

    @Column(name = "has_garage", nullable = false)
    private boolean hasGarage;

    @Column(name = "pets_allowed", nullable = false)
    private boolean petsAllowed;

    @Column(name = "more_information")
    private String moreInformation;

    // Constructor vacío (requerido por JPA)
    public PostProperty() {
    }

    // Constructor con campos obligatorios
    public PostProperty(User propertyOwner, PropertyAddress propertyAddress,
                        String price, String timeAgreement, String rentalType,
                        int roomsNumber, String servicesType) {
        this.propertyOwner = propertyOwner;
        this.propertyAddress = propertyAddress;
        this.price = price;
        this.timeAgreement = timeAgreement;
        this.rentalType = rentalType;
        this.roomsNumber = roomsNumber;
        this.servicesType = servicesType;
        this.hasServices = false;  // valor por defecto
        this.ownerLivesInProperty = false;  // valor por defecto
        this.hasGarage = false;  // valor por defecto
        this.petsAllowed = false;  // valor por defecto
    }
}
