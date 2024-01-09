package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.Academical;

import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.services.AcademicalService;
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
public class AcademicalController {
    
    @Autowired
    private final AcademicalService academicalService;

    public AcademicalController(AcademicalService academicalService) {
        this.academicalService = academicalService;
    }
    
    @PostMapping("/multiple")
    public ResponseEntity<Boolean> guardarListaAcademicas(@RequestBody List<Academical> academicals,
                                                           @RequestHeader("Authorization") String jwt) {
        try {
            academicalService.guardarListaAcademicas(academicals, jwt);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarAcademica(
            @RequestBody Academical academical,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(academicalService.guardarAcademica(academical, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/por-usuario/{usuarioId}")
    public ResponseEntity<List<Academical>> obtenerAcademicasPorUsuario(@PathVariable Long usuarioId) {
        User user = new User();
        user.setId(usuarioId);
        List<Academical> academicals = academicalService.obtenerAcademicasPorUsuario(user);
        return new ResponseEntity<>(academicals, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerListaAcademicas(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Academical> listaAcademicals = academicalService.obtenerListaAcademicas(jwt);
        return new ResponseEntity<>(listaAcademicals, HttpStatus.OK);
    }
    
    @GetMapping("/estados-academicos-unicos")
    public ResponseEntity<List<String>> obtenerEstadosAcademicosUnicos() {
        List<String> estadosAcademicos = academicalService.obtenerEstadosAcademicosUnicos();
        return new ResponseEntity<>(estadosAcademicos, HttpStatus.OK);
    }

    @PutMapping("/{academicaId}")
    public ResponseEntity<?> actualizarACademica(
            @PathVariable Long academicaId,
            @RequestBody Academical academical,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(academicalService.actualizarAcademica(academicaId, academical, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{academicaId}")
    public ResponseEntity<Boolean> deleteAcademica(
            @PathVariable Long academicaId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return new ResponseEntity<>(academicalService.deleteAcademica(academicaId,jwt), HttpStatus.OK);
    }


    @GetMapping("/{academicaId}")
    public ResponseEntity<Academical> obtenerAcademica(@PathVariable Long academicaId) {
        try {
            Academical academical = academicalService.obtenerAcademica(academicaId);
            if (academical != null) {
                return new ResponseEntity<>(academical, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
