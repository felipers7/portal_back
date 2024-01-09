package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REF_ACAD")
@Data
public class AcademicalReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_acad_id")
    private Long id;
    @Column(name = "ref_acad_nam")
    private String name;
    @Column(name = "ref_acad_ins")
    private String institution;
    @Column(name = "ref_acad_email")
    private String email;
    @Column(name = "ref_acad_phone")
    private String phone;


}
