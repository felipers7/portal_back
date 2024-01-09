package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_vrs_prd")
public class ProductVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vrs_id")
    private Long id;
    @Column(name = "vrs_name")
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "prd_id")
    private Product product;



}
