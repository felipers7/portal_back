package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.City;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    public City getReferenceById(Long id);

    List<City> findAll();

    List<City> findAllByState(State state);

    List<City> findByStateId(Long stateId);
    List<City> getCitiesByState(Long stateId);
}
