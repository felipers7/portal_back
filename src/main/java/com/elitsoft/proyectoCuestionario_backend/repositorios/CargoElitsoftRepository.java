
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoElitsoft;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoElitsoftRepository extends JpaRepository <CargoElitsoft, Long>{
    
    // Método para listar todos los cargogs disponibles en Elitsoft 
    List<CargoElitsoft> findAll();
    
}
