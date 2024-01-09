package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.repositories.AcademicalRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.AcademicalService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class AcademicalServiceImpl implements AcademicalService {
    
    private final AcademicalRepository academicalRepository;
    private final UserRepository userRepository;
    @Autowired
    private final UserService userService;

    public AcademicalServiceImpl(AcademicalRepository academicalRepository, UserRepository userRepository, UserService userService) {
        this.academicalRepository = academicalRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public Boolean guardarAcademica(Academical academical, String jwt) throws Exception {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        academical.setUser(userOptional.get());

      //  if (academica.getReferenciaAcademicas() != null) {
        //    for (ReferenciaAcademica referencia : academica.getReferenciaAcademicas()) {
          //      referencia.setAcademica(academica);
        //    }
      //  }

        academicalRepository.save(academical);
        return true;
    }
    
    @Override
    public Boolean guardarListaAcademicas(List<Academical> academicals, String jwt)  {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }

        academicalRepository.findByUser(userOptional.get())
                .forEach((academicalRepository::delete));

        academicals.forEach(academica -> {
            academica.setUser(userOptional.get());
            academicalRepository.save(academica);
        });



        return true;
    }
    
    @Override
    public List<Academical> obtenerAcademicasPorUsuario(User usr_id) {
        return academicalRepository.findByUser(usr_id);
    }

    @Override
    public Boolean actualizarAcademica(Long academicaId, Academical academical, String jwt) throws Exception{
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }


        academical.getAcademicalReference().forEach(a -> {
        System.out.println(a.toString());
        });

        academical.setId(academicaId);
        academical.setUser(userOptional.get());

        academicalRepository.save(academical);
        return true;
    }

    @Override
    public List<Academical> obtenerListaAcademicas(String jwt) {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Academical> academicals = academicalRepository.findByUser(userOptional.get());
        if(academicals == null){
            return Collections.emptyList();
        }

        return academicals;
    }
    
    @Override
    public List<String> obtenerEstadosAcademicosUnicos() {
        return academicalRepository.findAllDistinctStatus();
    }

    @Override
    public Boolean deleteAcademica(Long academicaId, String jwt) throws Exception {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Academical> academicaOld = academicalRepository.findById(academicaId);
        if( !academicaOld.isPresent()){
            throw new EntityNotFoundException("No se encontró la entidad laboral");
        }

        if(!academicaOld.get().getUser().getId().equals(userOptional.get().getId())){
            throw new AccessDeniedException("Este usuario no está autorizado para actualizar este entidad");
        }

        academicalRepository.deleteById(academicaId);
        return true;
    }

    @Override
    public void eliminarAcademicasPorUsuario(Long usuarioId) {
        Optional<User> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Academical> academicals = academicalRepository.findByUser(usuario.get());
            academicalRepository.deleteAll(academicals);
        }
    }


    @Override
    public Academical obtenerAcademica(Long academicaId) {
        Optional<Academical> academicaOptional = academicalRepository.findById(academicaId);
        if (academicaOptional.isPresent()) {
            return academicaOptional.get();
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con el ID: " + academicaId);
        }
    }



}
