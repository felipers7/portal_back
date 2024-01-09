package com.elitsoft.proyectoCuestionario_backend.config.jwt;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;
    private String password;

}
