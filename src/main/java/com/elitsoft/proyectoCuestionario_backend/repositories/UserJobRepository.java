package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maeva Martínez
 */
public interface UserJobRepository extends JpaRepository <UserJob, Long>{
    
    // Método para guardar una cargo, asociado a un usuario
    UserJob save(UserJob cargo);

    // Método para obtener todos los cargos por usuario
    List<UserJob> findByUser(User user);

    // Método para listar todos los cargos postulados 
    List<UserJob> findAll();
    
}
