package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_adt_usr_upd")
@Data
public class UserAuditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adt_usr_upd_id")
    private Long id;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User user;

    @Column(name = "usr_up_id")
    private Long responsibleId;
}
