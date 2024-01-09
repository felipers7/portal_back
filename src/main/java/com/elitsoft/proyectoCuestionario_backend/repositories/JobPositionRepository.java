
package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.JobPosition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maeva Martínez
 */
public interface JobPositionRepository extends JpaRepository <JobPosition, Long>{
    
    // Método para listar todos los cargos disponibles en Elitsoft
    List<JobPosition> findAll();

    // para retornar un boolean
    JobPosition save(JobPosition cargo);

    void deleteById(Long cargo);

}
