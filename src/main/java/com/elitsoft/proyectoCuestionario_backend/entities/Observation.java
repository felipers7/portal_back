package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TBL_OBS")
@Data
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obs_id")
    private Long id;
    @Column(name = "obs_desc")
    private String description;
    @Column(name = "obs_date")


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    @JsonBackReference
    private UserJob userJob;
    @Column(name = "usr_id_obs_cre")
    private Long responsibleId;

    @OneToMany(mappedBy = "observation", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ObservationUpdate> updates;



}
