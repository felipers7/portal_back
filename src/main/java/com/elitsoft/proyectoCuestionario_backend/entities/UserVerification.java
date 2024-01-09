package com.elitsoft.proyectoCuestionario_backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_usr_ver")
@Data
public class UserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_ver_id")
    private Long id;
    @Column(name = "usr_is_ver")
    private Boolean isVerified;
    @Column(name = "usr_ver_code")
    private String code;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usr_ver_date")
    private Date verificationDate;
}
