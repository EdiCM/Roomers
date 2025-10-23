package com.example.room_rental.controller;

import com.example.room_rental.model.PropertyAddress;
import com.example.room_rental.repository.PropertyAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/property-address")
public class PropertyAddressController {

    @Autowired
    private PropertyAddressRepository propertyAddressRepository;

    // CREAR PROPERTY ADDRESS
    @PostMapping
    public PropertyAddress createPropertyAddress(@RequestBody PropertyAddress addressRequest) {
        // Crear PropertyAddress con todos los campos (todos son obligatorios)
        PropertyAddress propertyAddress = new PropertyAddress(
                addressRequest.getLatitude(),
                addressRequest.getLongitude(),
                addressRequest.getCountry(),
                addressRequest.getState(),
                addressRequest.getCity(),
                addressRequest.getSuburb()
        );

        return propertyAddressRepository.save(propertyAddress);
    }

    // DEVUELVEME TODAS LAS DIRECCIONES
    @GetMapping
    public List<PropertyAddress> getAllPropertyAddresses() {
        return propertyAddressRepository.findAll();
    }

    // DEVUELVEME DIRECCION POR ID
    @GetMapping("/{id}")
    public PropertyAddress getPropertyAddressById(@PathVariable UUID id) {
        return propertyAddressRepository.findById(id).orElse(null);
    }

    // ACTUALIZA DIRECCION POR ID
    @PutMapping("/{id}")
    public PropertyAddress updatePropertyAddress(@PathVariable UUID id, @RequestBody PropertyAddress updatedAddress) {
        PropertyAddress propertyAddress = propertyAddressRepository.findById(id).orElse(null);
        if (propertyAddress != null) {
            // Actualizar directamente con los getters del modelo
            propertyAddress.setLatitude(updatedAddress.getLatitude());
            propertyAddress.setLongitude(updatedAddress.getLongitude());
            propertyAddress.setCountry(updatedAddress.getCountry());
            propertyAddress.setState(updatedAddress.getState());
            propertyAddress.setCity(updatedAddress.getCity());
            propertyAddress.setSuburb(updatedAddress.getSuburb());

            return propertyAddressRepository.save(propertyAddress);
        }
        return null;
    }

    // ELIMINA DIRECCION POR ID
    @DeleteMapping("/{id}")
    public void deletePropertyAddress(@PathVariable UUID id) {
        propertyAddressRepository.deleteById(id);
    }
}
