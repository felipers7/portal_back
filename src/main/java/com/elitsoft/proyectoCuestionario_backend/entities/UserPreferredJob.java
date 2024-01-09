package com.elitsoft.proyectoCuestionario_backend.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pref_job_usr")
@Data
public class UserPreferredJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pref_job_usr_id")
    private Long id;
    @Column(name = "pref_job_usr_desc")
    private String description;
}
