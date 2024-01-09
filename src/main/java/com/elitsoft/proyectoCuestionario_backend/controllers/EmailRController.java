package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.dto.MassiveEmailRequestDTO;
import com.elitsoft.proyectoCuestionario_backend.services.EmailRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emailR")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailRController {

    @Autowired
    private EmailRService emailRService;

    @PostMapping("/enviar-correo")
    public ResponseEntity<String> enviarCorreo(@RequestBody MassiveEmailRequestDTO massiveEmailRequestDTO) {
        try {
            // Llamar al método enviarCorreo del servicio
            emailRService.enviarCorreo(massiveEmailRequestDTO);

            return new ResponseEntity<String>("Ok, enviados correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al enviar el correo: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
