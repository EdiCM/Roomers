package com.example.room_rental.controller;

import com.example.room_rental.model.PostColiving;
import com.example.room_rental.model.PostProperty;
import com.example.room_rental.model.User;
import com.example.room_rental.repository.PostColivingRepository;
import com.example.room_rental.repository.PostPropertyRepository;
import com.example.room_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/post-coliving")
public class PostColivingController {

    @Autowired
    private PostColivingRepository postColivingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostPropertyRepository postPropertyRepository;

    // CREAR COLIVING POST
    @PostMapping("/{userId}/{propertyId}")
    public PostColiving createColiving(
            @PathVariable UUID userId,
            @PathVariable UUID propertyId,
            @RequestBody PostColiving colivingRequest) {

        // Buscar el usuario que hace la publicación
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        // Buscar la propiedad referenciada
        PostProperty property = postPropertyRepository.findById(propertyId).orElse(null);
        if (property == null) {
            return null;
        }

        // Crear Coliving con todos los campos obligatorios
        PostColiving postColiving = new PostColiving(
                user,
                property,
                colivingRequest.getDescription()
        );

        return postColivingRepository.save(postColiving);
    }

    // DEVUELVEME TODOS LOS COLIVING POSTS
    @GetMapping
    public List<PostColiving> getAllColivings() {
        return postColivingRepository.findAll();
    }

    // DEVUELVEME COLIVING POST POR ID
    @GetMapping("/{id}")
    public PostColiving getColivingById(@PathVariable UUID id) {
        return postColivingRepository.findById(id).orElse(null);
    }

    // ACTUALIZA COLIVING POST POR ID
    @PutMapping("/{id}")
    public PostColiving updateColiving(@PathVariable UUID id, @RequestBody PostColiving updatedColiving) {
        PostColiving postColiving = postColivingRepository.findById(id).orElse(null);
        if (postColiving != null) {
            // Actualizar solo la descripción (las relaciones no deberían cambiar)
            postColiving.setDescription(updatedColiving.getDescription());

            return postColivingRepository.save(postColiving);
        }
        return null;
    }

    // ELIMINA COLIVING POST POR ID
    @DeleteMapping("/{id}")
    public void deleteColiving(@PathVariable UUID id) {
        postColivingRepository.deleteById(id);
    }
}