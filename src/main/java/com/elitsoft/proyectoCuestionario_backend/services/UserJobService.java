package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.UserJob;
import com.elitsoft.proyectoCuestionario_backend.entities.User;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface UserJobService {

    Boolean guardarCargo(UserJob cargo, String jwt, Date fechaPostulacion) throws Exception;
    List<UserJob> obtenerCargosPorUsuario(User user);
    
    List<UserJob> obtenerListaCargos();

    UserJob obtenerCargoUsuario(String jwt) throws Exception;

    void eliminarCargoPorUsuario(Long usuarioId);


}
