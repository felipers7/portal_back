package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserJobRepository;
import com.elitsoft.proyectoCuestionario_backend.services.UserJobService;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martinez
 */
@Service
public class UserJobServiceImpl implements UserJobService {
    
    private final UserRepository userRepository;
    private final UserJobRepository cargoRepository;

    @Autowired
    private final UserService userService;

    public UserJobServiceImpl(UserRepository userRepository,
                              UserJobRepository cargoRepository,
                              UserService userService) {
        this.userRepository = userRepository;
        this.cargoRepository = cargoRepository;
        this.userService = userService;
    }

    @Override
    public Boolean guardarCargo(UserJob cargo, String jwt, Date fechaPostulacion) throws Exception {

        Optional<User> usuarioOptional = userService.getUsuarioByToken(jwt);
        if(!usuarioOptional.isPresent()){
            return false;
        }
        cargo.setUser(usuarioOptional.get());

        cargo.setApplicationDate(fechaPostulacion);

        cargoRepository.findByUser(usuarioOptional.get())
                .forEach((cargoRepository::delete));


        cargoRepository.save(cargo);
        return true;
    }


    @Override
    public List<UserJob> obtenerCargosPorUsuario(User usr_id) {
        return cargoRepository.findByUser(usr_id);
    }

    @Override
    public List<UserJob> obtenerListaCargos() {
        return cargoRepository.findAll();
    }

    @Override
    public UserJob obtenerCargoUsuario(String jwt) throws Exception {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<UserJob> userJob = cargoRepository.findByUser(userOptional.get());
        if(userJob == null){
            throw new EntityNotFoundException();
        }
        if(userJob.isEmpty()){
            return new UserJob();
        }

        return userJob.get(0);
    }

    @Override
    public void eliminarCargoPorUsuario(Long usuarioId) {
        Optional<User> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<UserJob> cargos = cargoRepository.findByUser(usuario.get());
            cargoRepository.deleteAll(cargos);
        }
    }


}
