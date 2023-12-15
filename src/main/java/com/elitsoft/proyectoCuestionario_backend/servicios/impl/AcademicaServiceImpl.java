package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.AcademicaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class AcademicaServiceImpl implements AcademicaService {
    
    private final AcademicaRepository academicaRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final UsuarioService usuarioService;

    public AcademicaServiceImpl(AcademicaRepository academicaRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.academicaRepository = academicaRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Boolean guardarAcademica(Academica academica, String jwt) throws Exception {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        academica.setUser(userOptional.get());

      //  if (academica.getReferenciaAcademicas() != null) {
        //    for (ReferenciaAcademica referencia : academica.getReferenciaAcademicas()) {
          //      referencia.setAcademica(academica);
        //    }
      //  }

        academicaRepository.save(academica);
        return true;
    }
    
    @Override
    public Boolean guardarListaAcademicas(List<Academica> academicas, String jwt)  {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }

        academicaRepository.findByUsuario(userOptional.get())
                .forEach((academicaRepository::delete));

        academicas.forEach(academica -> {
            academica.setUser(userOptional.get());
            academicaRepository.save(academica);
        });



        return true;
    }
    
    @Override
    public List<Academica> obtenerAcademicasPorUsuario(User usr_id) {
        return academicaRepository.findByUsuario(usr_id);
    }

    @Override
    public Boolean actualizarAcademica(Long academicaId, Academica academica, String jwt) throws Exception{
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }


        academica.getReferenciaAcademicas().forEach(a -> {
        System.out.println(a.toString());
        });

        academica.setInf_acad_id(academicaId);
        academica.setUser(userOptional.get());

        academicaRepository.save(academica);
        return true;
    }

    @Override
    public List<Academica> obtenerListaAcademicas(String jwt) {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Academica> academicas = academicaRepository.findByUsuario(userOptional.get());
        if(academicas == null){
            return Collections.emptyList();
        }

        return academicas;
    }
    
    @Override
    public List<String> obtenerEstadosAcademicosUnicos() {
        return academicaRepository.findAllDistinctInfAcadEst();
    }

    @Override
    public Boolean deleteAcademica(Long academicaId, String jwt) throws Exception {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Academica> academicaOld = academicaRepository.findById(academicaId);
        if( !academicaOld.isPresent()){
            throw new EntityNotFoundException("No se encontró la entidad laboral");
        }

        if(!academicaOld.get().getUser().getUsr_id().equals(userOptional.get().getUsr_id())){
            throw new AccessDeniedException("Este usuario no está autorizado para actualizar este entidad");
        }

        academicaRepository.deleteById(academicaId);
        return true;
    }

    @Override
    public void eliminarAcademicasPorUsuario(Long usuarioId) {
        Optional<User> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Academica> academicas = academicaRepository.findByUsuario(usuario.get());
            academicaRepository.deleteAll(academicas);
        }
    }


    @Override
    public Academica obtenerAcademica(Long academicaId) {
        Optional<Academica> academicaOptional = academicaRepository.findById(academicaId);
        if (academicaOptional.isPresent()) {
            return academicaOptional.get();
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con el ID: " + academicaId);
        }
    }



}
