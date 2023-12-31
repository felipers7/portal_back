package com.sistema.PortalElitsoft.Controladores;

import com.sistema.PortalElitsoft.Entidades.Examen;
import com.sistema.PortalElitsoft.Entidades.Pregunta;
import com.sistema.PortalElitsoft.Servicios.ExamenService;
import com.sistema.PortalElitsoft.Servicios.PreguntaService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Noelid Chávez
 */
@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/examen/{exam_id}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("exam_id") Long exam_id){
        Examen examen = examenService.obtenerExamen(exam_id);
        Set<Pregunta> preguntas = examen.getPreguntas();

        List examenes = new ArrayList(preguntas);
        if(examenes.size() > Integer.parseInt(examen.getExam_n_preg())){
            examenes = examenes.subList(0,Integer.parseInt(examen.getExam_n_preg() + 1));
        }

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes); 
    /*  Examen examen = examenService.obtenerExamen(examenId);
    Set<Pregunta> preguntas = examen.getPreguntas();

    // Crear una lista aleatoria de preguntas
    List<Pregunta> preguntasAleatorias = new ArrayList<>(preguntas);
    Collections.shuffle(preguntasAleatorias);

    // Limitar el número de preguntas
    int numPreguntas = Math.min(preguntasAleatorias.size(), 5);
    preguntasAleatorias = preguntasAleatorias.subList(0, numPreguntas);

    return ResponseEntity.ok(preguntasAleatorias);
      */
    }

    @GetMapping("/{prg_id}")
    public Pregunta listarPreguntaPorId(@PathVariable("prg_id") Long prg_id){
        return preguntaService.obtenerPregunta(prg_id);
    }

    @DeleteMapping("/{prg_id}")
    public void eliminarPregunta(@PathVariable("prg_id") Long prg_id){
        preguntaService.eliminarPregunta(prg_id);
    }

    @GetMapping("/examen/todos/{examenId}")
    public ResponseEntity<?> listarPreguntaDelExamenComoAdministrador(@PathVariable("examenId") Long examenId){
        Examen examen = new Examen();
        examen.setExam_id(examenId);
        Set<Pregunta> preguntas = preguntaService.obtenerPreguntasDelExamen(examen);
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas){
        double puntosMaximos = 0;
        Integer respuestasCorrectas = 0;
        Integer intentos = 0;
        Integer intentosTotales = 0;

        for(Pregunta p : preguntas){
            Pregunta pregunta = this.preguntaService.listarPregunta(p.getPrg_id());
            if(pregunta.getPrg_resp().equals(p.getPrg_respDada())){
                respuestasCorrectas ++;
                double puntos = Double.parseDouble(preguntas.get(0).getExamen().getExam_ptos_max())/preguntas.size();
                puntosMaximos += puntos;
            }
            if(p.getPrg_respDada() != null){
                intentos++;
            }
            intentosTotales++;
        }

        Map<String,Object> respuestas = new HashMap<>();
        respuestas.put("puntosMaximos",puntosMaximos);
        respuestas.put("respuestasCorrectas",respuestasCorrectas);
        respuestas.put("intentos",intentos);
        respuestas.put("intentosTotales",intentosTotales);
        return ResponseEntity.ok(respuestas);
    }
}
