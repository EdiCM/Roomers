package com.example.room_rental.controller;

import com.example.room_rental.model.PostProperty;
import com.example.room_rental.model.User;
import com.example.room_rental.model.PropertyAddress;
import com.example.room_rental.repository.PostPropertyRepository;
import com.example.room_rental.repository.UserRepository;
import com.example.room_rental.repository.PropertyAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/post-property")
public class PostPropertyController {

    @Autowired
    private PostPropertyRepository postPropertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyAddressRepository propertyAddressRepository;

    // CREAR POST PROPERTY
    @PostMapping("/{userId}/{addressId}")
    public PostProperty createPostProperty(
            @PathVariable UUID userId,
            @PathVariable UUID addressId,
            @RequestBody PostProperty propertyRequest) {

        // Buscar el usuario (propietario)
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        // Buscar la dirección
        PropertyAddress address = propertyAddressRepository.findById(addressId).orElse(null);
        if (address == null) {
            return null;
        }

        // Crear PostProperty con campos obligatorios
        PostProperty postProperty = new PostProperty(
                user,
                address,
                propertyRequest.getPrice(),
                propertyRequest.getTimeAgreement(),
                propertyRequest.getRentalType(),
                propertyRequest.getRoomsNumber(),
                propertyRequest.getServicesType()
        );

        // Agregar campos opcionales/booleanos directamente desde el request
        postProperty.setHasServices(propertyRequest.isHasServices());
        postProperty.setOwnerLivesInProperty(propertyRequest.isOwnerLivesInProperty());
        postProperty.setHasGarage(propertyRequest.isHasGarage());
        postProperty.setPetsAllowed(propertyRequest.isPetsAllowed());
        postProperty.setMoreInformation(propertyRequest.getMoreInformation());

        return postPropertyRepository.save(postProperty);
    }

    // DEVUELVEME TODAS LAS PROPIEDADES
    @GetMapping
    public List<PostProperty> getAllPostProperties() {
        return postPropertyRepository.findAll();
    }

    // DEVUELVEME PROPIEDAD POR ID
    @GetMapping("/{id}")
    public PostProperty getPostPropertyById(@PathVariable UUID id) {
        return postPropertyRepository.findById(id).orElse(null);
    }

    // ACTUALIZA PROPIEDAD POR ID
    @PutMapping("/{id}")
    public PostProperty updatePostProperty(@PathVariable UUID id, @RequestBody PostProperty updatedProperty) {
        PostProperty postProperty = postPropertyRepository.findById(id).orElse(null);
        if (postProperty != null) {
            // Actualizar directamente con los getters del modelo
            postProperty.setPrice(updatedProperty.getPrice());
            postProperty.setTimeAgreement(updatedProperty.getTimeAgreement());
            postProperty.setRentalType(updatedProperty.getRentalType());
            postProperty.setRoomsNumber(updatedProperty.getRoomsNumber());
            postProperty.setHasServices(updatedProperty.isHasServices());
            postProperty.setServicesType(updatedProperty.getServicesType());
            postProperty.setOwnerLivesInProperty(updatedProperty.isOwnerLivesInProperty());
            postProperty.setHasGarage(updatedProperty.isHasGarage());
            postProperty.setPetsAllowed(updatedProperty.isPetsAllowed());
            postProperty.setMoreInformation(updatedProperty.getMoreInformation());

            return postPropertyRepository.save(postProperty);
        }
        return null;
    }

    // ELIMINA PROPIEDAD POR ID
    @DeleteMapping("/{id}")
    public void deletePostProperty(@PathVariable UUID id) {
        postPropertyRepository.deleteById(id);
    }
}
