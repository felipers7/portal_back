
package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.Country;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import com.elitsoft.proyectoCuestionario_backend.repositories.CountryRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.StateRepository;
import com.elitsoft.proyectoCuestionario_backend.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class CountryServiceImpl implements CountryService {
    
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;

    @Override
    public Country obtenerPaisId(Long id) {
        return countryRepository.getReferenceById(id);
    }

    @Override
    public List<Country> obtenerPaises(){
        return countryRepository.findAll();
    }

   /* @Override
    public List<State> obtenerEstadosPorPais(Long countryId) {
        return countryRepository.findStatesByCountryId(countryId);
    }*/
   @Override
   public List<State> getStatesByCountry(Long countryId) {
       return stateRepository.findByCountryId(countryId);
   }


    
}
