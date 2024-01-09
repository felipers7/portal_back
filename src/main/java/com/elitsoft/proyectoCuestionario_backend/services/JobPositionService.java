
package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.JobPosition;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface JobPositionService {
    
    List<JobPosition> obtenerListaCargosElitsoft();


    public Boolean guardar_cargos(JobPosition cargo);

    public Boolean remove_cargo(Long cargo);
}
