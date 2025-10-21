package com.example.room_rental.repository;

import com.example.room_rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Spring Boot te da estos métodos automáticamente:
    // - save(user)           → guardar/actualizar
    // - findAll()            → obtener todos
    // - findById(id)         → buscar por ID
    // - deleteById(id)       → eliminar por ID
    // - delete(user)         → eliminar objeto
    // - count()              → contar registros
    // - existsById(id)       → verificar si existe
}
