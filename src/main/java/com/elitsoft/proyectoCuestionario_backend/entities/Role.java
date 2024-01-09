package com.elitsoft.proyectoCuestionario_backend.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_role_usr")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role_name")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
