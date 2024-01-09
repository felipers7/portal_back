package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserJobApprovalId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "tp_apr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Approval approval;

    @ManyToOne
    @JoinColumn(name = "job_usr_id") // Nombre de la columna que será clave foránea para la tabla user
    @JsonIgnore
    private UserJob userJob;
}
