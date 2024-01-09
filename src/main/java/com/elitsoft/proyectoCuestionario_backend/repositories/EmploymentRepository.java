package com.elitsoft.proyectoCuestionario_backend.repositories;


import com.elitsoft.proyectoCuestionario_backend.entities.Employment;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maeva Martínez
 */
@Repository
public interface EmploymentRepository extends JpaRepository <Employment, Long>{
    
    // Método para guardar inf. laboral asociado a un usuario
    Employment save(Employment employment);

    // Método para listar toda la info laboral
    List<Employment> findAll();

    List<Employment> findByUser(User user);

}
