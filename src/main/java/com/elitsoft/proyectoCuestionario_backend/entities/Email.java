package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

@Data
public class Email {

    private String toEmail;
    private String subject;
    private String body;
    private String motivo;

    public Email() {
    }



}

