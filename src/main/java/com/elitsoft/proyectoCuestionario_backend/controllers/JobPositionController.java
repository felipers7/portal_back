package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.JobPosition;
import com.elitsoft.proyectoCuestionario_backend.services.JobPositionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Maeva Martínez
 */
@RestController
@RequestMapping("/cargoselitsoft")
public class JobPositionController {
    
    @Autowired
    private final JobPositionService jobPositionService;

    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<JobPosition>> obtenerListaCargosElitsoft() {
        List<JobPosition> cargosElitsoft = jobPositionService.obtenerListaCargosElitsoft();
        return new ResponseEntity<>(cargosElitsoft, HttpStatus.OK);
    }



    @PostMapping("/")
    public Boolean guardar_cargos (@RequestBody JobPosition tipo_de_cargo )  {
        System.out.println(tipo_de_cargo.getName());
        jobPositionService.guardar_cargos(tipo_de_cargo);
        return  true;
    }

    @DeleteMapping("/{cargo}")
    public Boolean remove_cargo(@PathVariable Long cargo){
        jobPositionService.remove_cargo(cargo);
        return true;
    }

}
