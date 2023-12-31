
package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Laboral;
import com.elitsoft.proyectoCuestionario_backend.servicios.LaboralService;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.servicios.HerramientaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/herramientas")
@CrossOrigin(origins = "http://localhost:4200")
public class HerramientaController {

    private final HerramientaService herramientaService;
    private final LaboralService laboralService;
    
    @Autowired
    public HerramientaController(HerramientaService herramientaService, LaboralService laboralService) {
        this.herramientaService = herramientaService;
        this.laboralService = laboralService;
    }


    @PostMapping("/")
    public ResponseEntity<Laboral> guardarLaboral(
            @RequestBody Laboral laboral,
            @RequestParam Long usr_id,
            @RequestParam List<Long> herramientaIds // Cambio aquí: aceptar una lista de IDs de herramientas
    ) {
        try {
            Laboral laboralGuardada = laboralService.guardarLaboral(laboral, usr_id, herramientaIds);
            return new ResponseEntity<>(laboralGuardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Herramienta>> obtenerListaHerramientas() {
        List<Herramienta> herramientas = herramientaService.obtenerListaHerramientas();
        return new ResponseEntity<>(herramientas, HttpStatus.OK);
    }
    
   // @GetMapping("/obtener-herramientas-con-productos/{usuarioId}")
   // public ResponseEntity<List<Object[]>> obtenerHerramientasConProductosPorUsuario(@PathVariable Long usuarioId) {
    //    List<Object[]> herramientasConProductos = herramientaService.obtenerHerramientasConProductosPorUsuario(usuarioId);
   //     return new ResponseEntity<>(herramientasConProductos, HttpStatus.OK);
   // }
}

