package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_obs_upd")
@Data
public class ObservationUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obs_upd_id")
    private Long id;
    @Column(name = "obs_usr_upd_id")
    private Long responsibleId;
    @Column(name = "obs_upd_new_desc")
    private String description;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "obs_updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "obs_id")
    private Observation observation;

}
