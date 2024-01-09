package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_recovery_tkn")
@Data
public class UserRecoveryToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recovery_tkn_id")
    private Long id;
    @Column(name = "recovery_tkn")
    private String token;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recovery_tkn_cre")
    private Date createdAt;
}
