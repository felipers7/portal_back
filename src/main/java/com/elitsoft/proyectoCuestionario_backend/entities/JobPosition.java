package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_JOB_POS")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_pos_id")
    private Long id;
    @Column(name = "job_pos_nom")
    private String name;

    
}
