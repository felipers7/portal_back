package com.elitsoft.proyectoCuestionario_backend.controllers;


import com.elitsoft.proyectoCuestionario_backend.entities.Employment;

import com.elitsoft.proyectoCuestionario_backend.services.EmploymentService;
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
@RequestMapping("/employment")
public class EmploymentController {

    @Autowired
    private EmploymentService employmentService;

    @PostMapping("/multiple")
    public ResponseEntity<?> guardarLaborales(
            @RequestBody List<Employment> laborales,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(employmentService.guardarLaborales(laborales, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarLaboral(
            @RequestBody Employment employment,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            employmentService.guardarLaboral(employment, jwt);
            return new ResponseEntity<>( true ,HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{laborarId}")
    public ResponseEntity<?> actualizarLaboral(
            @PathVariable Long laborarId,
            @RequestBody Employment employment,
            @RequestHeader("Authorization") String jwt
    ) {
        try {
            return new ResponseEntity<>(employmentService.actualizarLaboral(laborarId, employment, jwt), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
   public ResponseEntity<List<?>> obtenerListaLaboral(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Employment> listaEmployment = employmentService.obtenerListaLaboral(jwt);
        return new ResponseEntity<>(listaEmployment, HttpStatus.OK);
    }

    @GetMapping("/obtener/{laboralId}")
    public ResponseEntity<Employment> obtenerLaboral(@PathVariable Long laboralId, @RequestHeader("Authorization") String jwt) throws Exception {
        // Asumiendo que el método en tu servicio espera un ID y devuelve una instancia de Laboral
        Employment employment = employmentService.obtenerLaboralPorId(laboralId, jwt);
        if (employment != null) {
            return new ResponseEntity<>(employment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{laboralId}")
    public ResponseEntity<Boolean> deleteLaboral(
            @PathVariable Long laboralId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return new ResponseEntity<>(employmentService.deleteLaboral(laboralId,jwt), HttpStatus.OK);
    }





//    @GetMapping("/listar-por-usuario/{usuarioId}")
//    public ResponseEntity<List<Laboral>> obtenerListaLaboralPorUsuario(@PathVariable Long usuarioId) {
//        Usuario usuario = new Usuario();
//        usuario.setUsr_id(usuarioId);
//
//        List<Laboral> listaLaboral = laboralService.obtenerListaLaboralPorUsuario(usuario);
//        System.out.println(listaLaboral);
//
//        return new ResponseEntity<>(listaLaboral, HttpStatus.OK);
//    }

//    @GetMapping("/listar")
//    public ResponseEntity<List<Laboral>> obtenerListaLaboral() {
//        List<Laboral> listaLaboral = laboralService.obtenerListaLaboral();
//
//        return new ResponseEntity<>(listaLaboral, HttpStatus.OK);
//    }

//    @GetMapping("/listar-con-herramientas/{usuarioId}")
//    public ResponseEntity<List<Laboral>> obtenerLaboralesConHerramientasYProductosPorUsuario(@PathVariable Long usuarioId) {
//        try {
//            List<Laboral> laborales = laboralService.obtenerLaboralesConHerramientasYProductosPorUsuario(usuarioId);
//            return new ResponseEntity<>(laborales, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
}


