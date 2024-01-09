package com.elitsoft.proyectoCuestionario_backend.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class MassiveEmailRequestDTO {
    private List<String> emails;
    private String subject;
}
