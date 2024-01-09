package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.dto.MassiveEmailRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailRService {

    public void enviarCorreo(MassiveEmailRequestDTO massiveEmailRequestDTO);
}
