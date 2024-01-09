package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.dto.MassiveEmailRequestDTO;
import com.elitsoft.proyectoCuestionario_backend.services.EmailRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailRServiceImpl implements EmailRService {

    @Autowired
    private JavaMailSender javaMailSender;
    private final String remitente = "reclutamiento@elitsoft-chile.com";


    @Override
    public void enviarCorreo(MassiveEmailRequestDTO massiveEmailRequestDTO) {
        // Itera sobre los correos electrónicos y envía el correo a cada destinatario
        for (String toEmail : massiveEmailRequestDTO.getEmails()) {

            System.out.println(toEmail);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setFrom(remitente);
            message.setSubject(massiveEmailRequestDTO.getSubject());

            // Maneja la lógica específica para diferentes motivos
            switch (massiveEmailRequestDTO.getSubject()) {
                case "Solicitud de datos":
                    message.setText("Estimado/a destinatario/a,\n\n"
                            + "Estamos solicitando información adicional para completar su perfil. "
                            + "Por favor, proporcione los datos necesarios lo antes posible. Gracias.");
                    break;
                case "Contactar":
                    message.setText("Estimado/a destinatario/a,\n\n"
                            + "Nos gustaría ponernos en contacto para discutir asuntos importantes. "
                            + "Por favor, responda a este correo electrónico para coordinar una llamada o reunión. Gracias.");
                    break;
                case "Invitar a nueva postulación":
                    message.setText("Estimado/a destinatario/a,\n\n"
                            + "Le invitamos a participar en nuestro proceso de selección una vez más. "
                            + "Por favor, visite nuestro sitio web para obtener más detalles y presentar su solicitud. Gracias.");
                    break;
                case "Informar Resultados de proceso de selección":
                    message.setText("Estimado/a destinatario/a,\n\n"
                            + "Nos complace informarle que ha sido seleccionado/a para avanzar en nuestro proceso de selección. "
                            + "Pronto nos pondremos en contacto para coordinar la siguiente fase. ¡Felicidades!");
                    break;
                case "Informar que el usuario fue aprobado":
                    message.setText("Estimado/a destinatario/a,\n\n"
                            + "Es un placer para nosotros informarte que has sido seleccionado/a pare el cargo en Elitsoft. "
                            + "Felicitaciones por destacarte entre los candidatos y demostrar tus habilidades y experiencia durante el proceso de selección."
                            + " ¡Felicidades! 🎉");
                    break;
                default:
                    // Motivo no reconocido
                    throw new IllegalArgumentException("Motivo no reconocido: " + massiveEmailRequestDTO.getSubject());
            }

            try {
                javaMailSender.send(message);
                System.out.println("Correo enviado correctamente a " + toEmail + " con motivo: " + massiveEmailRequestDTO.getSubject());
            } catch (Exception e) {
                System.err.println("Error al enviar el correo a " + toEmail + " con motivo: " + massiveEmailRequestDTO.getSubject() + ": " + e.getMessage());
                throw new RuntimeException("Error al enviar el correo");
            }
        }
    }

}
