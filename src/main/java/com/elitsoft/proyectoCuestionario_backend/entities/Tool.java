package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_TOOL_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_usr_id")
    private Long id;
    @Column(name = "tool_usr_yrs_exp")
    private Integer yearsOfExperience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "vrs_id")
    private ProductVersion productVersion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_cert_id")
    private Certification certification;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToMany
    @JsonIgnore
    private List<Employment> employments;





}
