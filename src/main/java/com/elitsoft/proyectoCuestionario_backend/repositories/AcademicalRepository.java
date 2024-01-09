
package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.Academical;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Maeva Martínez
 */
public interface AcademicalRepository extends JpaRepository <Academical, Long> {
    
    // Método para guardar una inf. academica asociada a un usuario
    Academical save(Academical academical);
    
    // Método para obtener inf. academica por usuario
    List<Academical> findByUser(User user);

    // Método para listar todas las inf. academica
    List<Academical> findAll();
    
    //metodo para traer todos los estados de la tabla academica
    @Query("SELECT DISTINCT a.status FROM Academical a")
    List<String> findAllDistinctStatus();

    // para retornar un boolean
    //Boolean save(Academica academica);

    void deleteById(Long academica_id);


}
