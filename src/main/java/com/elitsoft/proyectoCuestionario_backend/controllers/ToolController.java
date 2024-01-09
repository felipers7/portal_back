
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.Employment;
import com.elitsoft.proyectoCuestionario_backend.entities.Tool;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CreateToolDTO;
import com.elitsoft.proyectoCuestionario_backend.services.EmploymentService;
import com.elitsoft.proyectoCuestionario_backend.services.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/herramientas")
@CrossOrigin(origins = "http://localhost:4200")
public class ToolController {

    private final ToolService toolService;
    private final EmploymentService employmentService;
    
    @Autowired
    public ToolController(ToolService toolService, EmploymentService employmentService) {
        this.toolService = toolService;
        this.employmentService = employmentService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createTool(
            @RequestBody CreateToolDTO tool,
            @RequestHeader("Authorization") String Jwt) throws Exception {
        try {
            toolService.createTool(tool, Jwt);
        }
        catch (ConstraintViolationException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerListaHerramientas(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Tool> tools = toolService.obtenerListaHerramientasPorUsuario(jwt);
        return new ResponseEntity<>(tools, HttpStatus.OK);
    }

    @DeleteMapping("/{toolId}")
    public ResponseEntity<?> actualizarLaboral(
            @PathVariable Long toolId,
            @RequestHeader("Authorization") String jwt
    ){
        return new ResponseEntity<>(toolService.deleteUserTool(toolId, jwt), HttpStatus.OK);
    }

}

