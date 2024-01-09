package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REF_emp")
@Data
public class EmploymentReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_emp_id")
    private Long id;
    @Column(name = "ref_emp_nom")
    private String name;
    @Column(name = "ref_emp_emp")
    private String company;
    @Column(name = "ref_emp_email")
    private String email;
    @Column(name = "ref_emp_phn")
    private String phone;


}
