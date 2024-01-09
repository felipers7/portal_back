package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.City;
import com.elitsoft.proyectoCuestionario_backend.entities.Country;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import com.elitsoft.proyectoCuestionario_backend.repositories.CityRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.StateRepository;
import com.elitsoft.proyectoCuestionario_backend.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public State obtenerEstadoId(Long id) {
        return stateRepository.getReferenceById(id);
    }

    @Override
    public List<State> obtenerEstados(){
        return stateRepository.findAll();
    }

    @Override

    public void guardarEstado(State state){
        stateRepository.save(state);
    }

    @Override
    public List<State> estadosbyCountryId(Long countryId) {
        Country country = new Country();
        country.setId(countryId);
        return stateRepository.findAllByCountry(country);
    }

    @Override
    public List<City> getCitiesByState(Long stateId) {
        return cityRepository.findByStateId(stateId);
    }

}
