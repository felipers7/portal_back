
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.Question;
import com.elitsoft.proyectoCuestionario_backend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 *
 * @author ELITSOFT86
 */
@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/")
    public ResponseEntity<Question> guardarPregunta(@RequestBody Question question){
        return ResponseEntity.ok(questionService.agregarPregunta(question));
    }
    @PutMapping("/actualizar/{preguntaId}")
    public ResponseEntity<Question> actualizarPregunta(@PathVariable Long preguntaId, @RequestBody Question question){
        Question questionActualizada = questionService.actualizarPreguntaId(preguntaId, question);
        return ResponseEntity.ok(questionActualizada);
    }
    @GetMapping("/{preguntaId}")
    public Question listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return questionService.obtenerPregunta(preguntaId);
    }

    @DeleteMapping("/eliminar/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        questionService.eliminarPregunta(preguntaId);
    }

    @GetMapping("/generarExamen")
    public ResponseEntity<List<Question>> generarExamen(
            @RequestParam String description,
            @RequestParam Long productId
    ) {
        List<Question> examQuestions = questionService.generarExamen(description, productId);
        return ResponseEntity.ok(examQuestions);
    }

    @GetMapping("/por-producto/{productoId}")
    public ResponseEntity<List<Question>> obtenerPreguntasPorProducto(@PathVariable Long productoId) {
        List<Question> preguntas = questionService.obtenerPreguntasPorProducto(productoId);
        return ResponseEntity.ok(preguntas);
    }








}
