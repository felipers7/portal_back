package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_availability_usr")
@Data
public class UserJobAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_usr_id")
    private Long id;
    @Column(name = "availability_usr_time")
    private String time;
}
