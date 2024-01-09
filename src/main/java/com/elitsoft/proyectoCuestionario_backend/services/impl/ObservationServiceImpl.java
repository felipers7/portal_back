package com.elitsoft.proyectoCuestionario_backend.services.impl;
import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CatObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.ObservacionDTO;
import com.elitsoft.proyectoCuestionario_backend.repositories.*;
import com.elitsoft.proyectoCuestionario_backend.entities.Observation;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.services.ObservationService;
import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

import java.util.*;

@Service
public class ObservationServiceImpl implements ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserService userService;


    @Override
    public Boolean guardarObservacionRec(Observation observation, Long userId, Long usr_id_obs, Long usr_id_obs_mod) {
        try {

            if (!usr_id_obs.equals(usr_id_obs_mod)) {
                throw new IllegalArgumentException("El usuario que crea y modifica la observación debe ser el mismo.");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));

              observationRepository.save(observation);

            return true;
        } catch (DataAccessException e) {

            e.printStackTrace();
            throw new RuntimeException("Error al guardar la observación en la base de datos.", e);
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Error desconocido al guardar la observación.", e);
        }
    }



    @Override
    public Observation actualizarObservacionRec(Long obs_id, Observation observationActualizada, Long usr_id_obs_mod) {
        try {
            Optional<Observation> observacionOptional = observationRepository.findById(obs_id);
            if (observacionOptional.isPresent()) {
                Observation observationExistente = observacionOptional.get();


                observationExistente.setDescription(observationActualizada.getDescription());

                return observationRepository.save(observationExistente);
            } else {
                throw new IllegalArgumentException("La observación con ID " + obs_id + " no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Considera manejar esta excepción de manera más específica.
            return null;
        }
    }
}








