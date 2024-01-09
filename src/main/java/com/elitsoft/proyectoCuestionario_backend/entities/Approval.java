package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "tbl_tp_apr")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_apr_id")
    private Long id;

    @Column(name = "tp_approval")
    private String name;
}
