package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.repositories.ToolRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.EmploymentRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.EmploymentService;

import java.util.*;

import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martinez
 */
@Service
public class EmploymentServiceImpl implements EmploymentService {
    
    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;
    private final ToolRepository toolRepository;
    @Autowired
    private final UserService userService;

    @Autowired
    public EmploymentServiceImpl(EmploymentRepository employmentRepository,
                                 UserRepository userRepository,
                                 ToolRepository toolRepository,
                                 UserService userService) {
        this.employmentRepository = employmentRepository;
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
        this.userService = userService;
    }

    @Override
    public Boolean guardarLaborales(List<Employment> laborales, String jwt) throws Exception {

        Optional<User> userOptional = userService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }

        employmentRepository.findByUser(userOptional.get())
                .forEach((employmentRepository::delete));

        laborales.forEach(laboral -> {
            laboral.setUser(userOptional.get());
            employmentRepository.save(laboral);
        });


        return true;
    }

    @Override
    public Boolean guardarLaboral(Employment employment, String jwt) throws Exception {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        employment.setUser(userOptional.get());
            //employment.getEmploymentReferences().forEach(r ->{
               /* System.out.println(r.toString());

            });*/

        employmentRepository.save(employment);
        return true;
    }

    @Override
    public Boolean actualizarLaboral(Long laboralId, Employment employment, String jwt) throws Exception{
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }
// TODO: 15-12-2023 Verificar que efectivamente esto pertenezca al usuario:
        employment.setId(laboralId);
        employment.setUser(userOptional.get());
        employmentRepository.save(employment);

        return true;
    }


    @Override
    public List<Employment> obtenerListaLaboral(String jwt) throws Exception{
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Employment> laborales = employmentRepository.findByUser(userOptional.get());
        if(laborales == null){
            return Collections.emptyList();
        }

        return laborales;
    }

    @Override
    public Boolean deleteLaboral(Long laboralId, String jwt) throws Exception {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Employment> laboralOld = employmentRepository.findById(laboralId);
        if( !laboralOld.isPresent()){
            throw new EntityNotFoundException("No se encontró la entidad laboral");
        }

        if(!laboralOld.get().getUser().getId().equals(userOptional.get().getId())){
            throw new AccessDeniedException("Este usuario no está autorizado para actualizar este entidad");
        }

        employmentRepository.deleteById(laboralId);
        return true;
    }


    @Override
    public void eliminarLaboralPorUsuario(Long usuarioId) {
        Optional<User> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Employment> laborales = employmentRepository.findByUser(usuario.get());
            employmentRepository.deleteAll(laborales);
        }
    }



    @Override
    public Employment obtenerLaboralPorId(Long laboralId, String jwt) throws Exception {
        // Aquí verificas el JWT y luego obtienes el objeto Laboral
        Optional<User> usuario = userService.getUsuarioByToken(jwt);
        if (!usuario.isPresent()) {
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Employment> laboral = employmentRepository.findById(laboralId);
        if (!laboral.isPresent()) {
            throw new EntityNotFoundException("No se encontró la información laboral con el ID proporcionado");
        }

        // Puedes agregar lógica adicional aquí para verificar si el usuario tiene permiso para acceder a este Laboral
        // ...

        return laboral.get();
    }


}
