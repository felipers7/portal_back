package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.Country;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State getReferenceById(Long id);

    List<State> findAll();

    List<State> findByCountryId(Long countryId);
    List<State> findAllByCountry(Country country);




}