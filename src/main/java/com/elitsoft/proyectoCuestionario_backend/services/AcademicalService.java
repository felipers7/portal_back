
package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Academical;
import com.elitsoft.proyectoCuestionario_backend.entities.User;

import java.util.List;

/**
 *
 * @author Martinez Maeva
 */
public interface AcademicalService {

    Boolean guardarListaAcademicas(List<Academical> academicals, String jwt) throws Exception;

    Boolean guardarAcademica(Academical academical, String jwt) throws Exception;

    List<Academical> obtenerAcademicasPorUsuario(User user);

    List<Academical> obtenerListaAcademicas(String jwt) throws Exception;

    Academical obtenerAcademica(Long academicaId);

    Boolean actualizarAcademica(Long academicaId, Academical academical, String jwt) throws Exception;

    List<String> obtenerEstadosAcademicosUnicos();

    Boolean deleteAcademica(Long academicaId, String jwt) throws Exception;

    void eliminarAcademicasPorUsuario(Long usuarioId);
}