package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.City;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StateService {

    public State obtenerEstadoId(Long id);

    List<State> obtenerEstados();

    void guardarEstado(State state);

    List <State> estadosbyCountryId(Long countryId);

    List<City> getCitiesByState(Long stateId);

}