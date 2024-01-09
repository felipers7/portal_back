
package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.JobPosition;
import com.elitsoft.proyectoCuestionario_backend.repositories.JobPositionRepository;
import com.elitsoft.proyectoCuestionario_backend.services.JobPositionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class JobPositionServiceImpl implements JobPositionService {
    
    @Autowired
    private final JobPositionRepository jobPositionRepository;

    public JobPositionServiceImpl(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }
    
    @Override
    public List<JobPosition> obtenerListaCargosElitsoft() {
        return jobPositionRepository.findAll();
    }

    @Override
    public Boolean guardar_cargos(JobPosition cargo){
        jobPositionRepository.save(cargo);
        return true;
    }

    @Override
    public Boolean remove_cargo(Long cargo) {
        jobPositionRepository.deleteById(cargo);
        return true;

    }

}
