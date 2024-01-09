package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_usr_cert")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column(name = "usr_cert_url")
    private String url;
}
