package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.dto.CatObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservacionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObservationService {


    Boolean guardarObservacionRec(Observation observation, Long userId, Long usr_id_obs, Long usr_id_obs_mod); // felipe


    Observation actualizarObservacionRec(Long obs_id, Observation observationActualizada, Long usr_id_obs_mod); //felipe





}
