package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    public City obtenerCiudadId(Long id);

    List<City> obtenerCiudades();

    List<City> obtenerCiudadesPorEstado(Long stateId);
}
