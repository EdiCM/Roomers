package com.example.room_rental.controller;

import com.example.room_rental.model.PropertyPhoto;
import com.example.room_rental.model.PostProperty;
import com.example.room_rental.repository.PropertyPhotoRepository;
import com.example.room_rental.repository.PostPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/property-photo")
public class PropertyPhotoController {

    @Autowired
    private PropertyPhotoRepository propertyPhotoRepository;

    @Autowired
    private PostPropertyRepository postPropertyRepository;

    // SUBIR UNA FOTO A UNA PROPIEDAD
    // consumes = MULTIPART_FORM_DATA: Indica que este endpoint recibe ARCHIVOS (no JSON)
    @PostMapping(value = "/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PropertyPhoto uploadPropertyPhoto(
            @PathVariable UUID propertyId,
            // MultipartFile: Tipo especial de Spring para recibir archivos
            @RequestParam("photo") MultipartFile photo) throws IOException {

        // Buscar la propiedad
        PostProperty property = postPropertyRepository.findById(propertyId).orElse(null);
        if (property == null) {
            return null;
        }

        // Crear nueva foto
        // photo.getBytes() convierte el archivo a un array de bytes (binario)
        PropertyPhoto propertyPhoto = new PropertyPhoto(property, photo.getBytes());

        return propertyPhotoRepository.save(propertyPhoto);
    }

    // OBTENER TODAS LAS FOTOS DE UNA PROPIEDAD (para mostrar en galería)
    @GetMapping("/property/{propertyId}")
    public List<PropertyPhoto> getPhotosByProperty(@PathVariable UUID propertyId) {
        return propertyPhotoRepository.findByProperty_PropertyId(propertyId);
    }

    // OBTENER UNA FOTO ESPECÍFICA COMO IMAGEN
    // produces = IMAGE_JPEG: Indica que este endpoint devuelve una IMAGEN (no JSON)
    @GetMapping(value = "/{photoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPropertyPhoto(@PathVariable UUID photoId) {
        PropertyPhoto propertyPhoto = propertyPhotoRepository.findById(photoId).orElse(null);
        // Devuelve el array de bytes que representa la imagen
        return propertyPhoto != null ? propertyPhoto.getPhotoPath() : null;
    }

    // ELIMINAR UNA FOTO ESPECÍFICA
    @DeleteMapping("/{photoId}")
    public void deletePropertyPhoto(@PathVariable UUID photoId) {
        propertyPhotoRepository.deleteById(photoId);
    }

    // ELIMINAR TODAS LAS FOTOS DE UNA PROPIEDAD (si borras la propiedad)
    @DeleteMapping("/property/{propertyId}")
    public void deleteAllPropertyPhotos(@PathVariable UUID propertyId) {
        List<PropertyPhoto> photos = propertyPhotoRepository.findByProperty_PropertyId(propertyId);
        propertyPhotoRepository.deleteAll(photos);
    }
}
