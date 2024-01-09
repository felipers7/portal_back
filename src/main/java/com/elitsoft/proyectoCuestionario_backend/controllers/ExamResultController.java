package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.ExamResult;
import com.elitsoft.proyectoCuestionario_backend.services.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultados")
@CrossOrigin("*")
public class ExamResultController {
    @Autowired
    private ExamResultService examResultService;

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerResultados(){
        List<ExamResult> resultados= examResultService.obtenerResultados();
        return new ResponseEntity<>(resultados, HttpStatus.OK);

    }



    @GetMapping("/{idUser}")
    public ResponseEntity<List<?>> obtenerResultadosByUser(@PathVariable Long idUser){
        List<ExamResult> resultados = examResultService.obtenerResultadosByUser(idUser);
        return new ResponseEntity<>(resultados, HttpStatus.OK);

    }

    @PostMapping("/")
    public Boolean guardarResultados (@RequestBody ExamResult examResult,
                                      @RequestHeader("Authorization") String jwt)  {
        examResultService.guardarResultados(examResult, jwt);
        return true;
    }


}