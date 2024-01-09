package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "tbl_gen")
@Data
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gen_id")
    private Long id;
    @Column(name = "gen_name")
    private String name;
}
