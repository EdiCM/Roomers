package com.example.room_rental.controller;

import com.example.room_rental.model.UserPhoto;
import com.example.room_rental.model.User;
import com.example.room_rental.repository.UserPhotoRepository;
import com.example.room_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-photo")
public class UserPhotoController {

    @Autowired
    private UserPhotoRepository userPhotoRepository;

    @Autowired
    private UserRepository userRepository;

    // SUBIR O ACTUALIZAR FOTO
    // consumes = MULTIPART_FORM_DATA: Indica que este endpoint recibe ARCHIVOS (no JSON)
    // Esto es necesario para subir imágenes, PDFs, videos, etc.
    @PostMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserPhoto uploadUserPhoto(
            @PathVariable UUID userId,
            // MultipartFile: Tipo especial de Spring para recibir archivos
            // En Postman/Frontend usarías form-data con key "photo" y seleccionas el archivo
            // throws IOException manda http 500 error si algo falla
            @RequestParam("photo") MultipartFile photo) throws IOException {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        // Si ya existe, actualiza; si no, crea
        UserPhoto userPhoto = userPhotoRepository.findById(userId).orElse(null);

        if (userPhoto != null) {
            userPhoto.setPhotoPath(photo.getBytes());
        } else {
            userPhoto = new UserPhoto(user, photo.getBytes());
        }

        return userPhotoRepository.save(userPhoto);
    }

    // OBTENER FOTO DE UN USUARIO
    @GetMapping(value = "/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserPhoto(@PathVariable UUID userId) {
        UserPhoto userPhoto = userPhotoRepository.findById(userId).orElse(null);
        return userPhoto != null ? userPhoto.getPhotoPath() : null;
    }

    // ELIMINAR FOTO
    @DeleteMapping("/{userId}")
    public void deleteUserPhoto(@PathVariable UUID userId) {
        userPhotoRepository.deleteById(userId);
    }
}
