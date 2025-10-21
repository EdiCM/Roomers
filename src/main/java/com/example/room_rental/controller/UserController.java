package com.example.room_rental.controller;

import com.example.room_rental.model.User;
import com.example.room_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


import java.util.List;

@RestController // dice que es un controlador de tipo RESTful
@RequestMapping("/api/users") // todas las url se llamaran empezando asi
public class UserController {

    @Autowired
    private UserRepository userRepository; // inyecta automaticamente una instancia UserRepository

    // CREAR USUARIO
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    // DEVUELVEME TODOS LOS USUARIOS
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // DEVUELVEME UN USUARIO POR ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    // ACTUALIZA PASSWORD DE USUARIO POR ID
    @PutMapping("/{id}/password")
    public User updatePassword(@PathVariable UUID id, @RequestBody String newPassword) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(newPassword);
            return userRepository.save(user);
        }
        return null;
    }

    // ELIMINA USUARIO POR ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userRepository.deleteById(id);
    }
}