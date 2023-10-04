package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Academica;

import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Maeva Martinez
 */
@RestController
@RequestMapping("/academicas")
public class AcademicaController {
    
    @Autowired
    private final AcademicaService academicaService;

    public AcademicaController(AcademicaService academicaService) {
        this.academicaService = academicaService;
    }
    
    @PostMapping("/multiple")
    public ResponseEntity<Boolean> guardarListaAcademicas(@RequestBody List<Academica> academicas,
                                                           @RequestHeader("Authorization") String jwt) {
        try {
            academicaService.guardarListaAcademicas(academicas, jwt);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarAcademica(
            @RequestBody Academica academica,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(academicaService.guardarAcademica(academica, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/por-usuario/{usuarioId}")
    public ResponseEntity<List<Academica>> obtenerAcademicasPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setUsr_id(usuarioId);
        List<Academica> academicas = academicaService.obtenerAcademicasPorUsuario(usuario);
        return new ResponseEntity<>(academicas, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerListaAcademicas(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Academica> listaAcademicas = academicaService.obtenerListaAcademicas(jwt);
        return new ResponseEntity<>(listaAcademicas, HttpStatus.OK);
    }
    
    @GetMapping("/estados-academicos-unicos")
    public ResponseEntity<List<String>> obtenerEstadosAcademicosUnicos() {
        List<String> estadosAcademicos = academicaService.obtenerEstadosAcademicosUnicos();
        return new ResponseEntity<>(estadosAcademicos, HttpStatus.OK);
    }

    @PutMapping("/{academicaId}")
    public ResponseEntity<?> actualizarLaboral(
            @PathVariable Long academicaId,
            @RequestBody Academica academica,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(academicaService.actualizarAcademica(academicaId, academica, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
}
