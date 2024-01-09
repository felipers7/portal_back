package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Employment;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface EmploymentService {
    
    Boolean guardarLaborales(List<Employment> employment, String jwt) throws Exception;
    ///List<Laboral> obtenerListaLaboralPorUsuario(Usuario usuario);

    Boolean guardarLaboral(Employment employment, String jwt) throws Exception;

    Boolean actualizarLaboral(Long laboralId, Employment employment, String jwt) throws Exception;
    
    List<Employment> obtenerListaLaboral(String jwt) throws Exception;

    Boolean deleteLaboral(Long laboralId, String jwt)throws Exception;

    void eliminarLaboralPorUsuario(Long usuarioId);


    Employment obtenerLaboralPorId(Long laboralId, String jwt) throws Exception;


}
