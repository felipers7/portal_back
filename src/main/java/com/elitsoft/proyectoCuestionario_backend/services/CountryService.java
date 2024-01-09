
package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Country;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author ELITSOFT86
 */
@Service
public interface CountryService {
    
    public Country obtenerPaisId(Long id);
    List<Country> obtenerPaises();
    //List<State> obtenerEstadosPorPais(Long countryId);

    List<State> getStatesByCountry(Long countryId);
    
}
