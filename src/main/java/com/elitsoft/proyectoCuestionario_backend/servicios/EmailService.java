package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
    public void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException;
    public void sendRecoverPassword(User user) throws MessagingException, UnsupportedEncodingException;
}
