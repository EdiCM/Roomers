package com.example.room_rental.controller;

import com.example.room_rental.model.User;
import com.example.room_rental.model.UserData;
import com.example.room_rental.repository.UserDataRepository;
import com.example.room_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-data")
public class UserDataController {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserRepository userRepository;

    // CREAR USER DATA
    @PostMapping("/{userId}")
    public UserData createUserData(@PathVariable UUID userId, @RequestBody UserData userDataRequest) {
        // Buscar el usuario
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        // Crear UserData con campos obligatorios
        UserData userData = new UserData(
                user,
                userDataRequest.getName(),
                userDataRequest.getAgeRange(),
                userDataRequest.getGender(),
                userDataRequest.getAboutMe()
        );

        // Agregar campos opcionales directamente desde el request
        userData.setOccupation(userDataRequest.getOccupation());
        userData.setHasPet(userDataRequest.isHasPet());
        userData.setPetType(userDataRequest.getPetType());
        userData.setSmoker(userDataRequest.isSmoker());

        return userDataRepository.save(userData);
    }

    // DEVUELVEME TODOS LOS USER DATA
    @GetMapping
    public List<UserData> getAllUserData() {
        return userDataRepository.findAll();
    }

    // DEVUELVEME USER DATA POR ID
    @GetMapping("/{id}")
    public UserData getUserDataById(@PathVariable UUID id) {
        return userDataRepository.findById(id).orElse(null);
    }

    // ACTUALIZA USER DATA POR ID
    @PutMapping("/{id}")
    public UserData updateUserData(@PathVariable UUID id, @RequestBody UserData updatedData) {
        UserData userData = userDataRepository.findById(id).orElse(null);
        if (userData != null) {
            // Actualizar directamente con los getters del modelo
            userData.setName(updatedData.getName());
            userData.setAgeRange(updatedData.getAgeRange());
            userData.setGender(updatedData.getGender());
            userData.setAboutMe(updatedData.getAboutMe());
            userData.setOccupation(updatedData.getOccupation());
            userData.setHasPet(updatedData.isHasPet());
            userData.setPetType(updatedData.getPetType());
            userData.setSmoker(updatedData.isSmoker());

            return userDataRepository.save(userData);
        }
        return null;
    }

    // ELIMINA USER DATA POR ID
    @DeleteMapping("/{id}")
    public void deleteUserData(@PathVariable UUID id) {
        userDataRepository.deleteById(id);
    }
}
