package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_usr_cv")
@Data
public class UserCV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_cv_id")
    private Long id;
    @Column(name = "usr_cv_path")
    private String path;
}
