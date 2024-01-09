
package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;


/**
 *
 * @author Elitsoft83
 */
@Entity
@Table(name = "TBL_USR")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;
    @Column(name = "usr_rut")
    private String rut;
    @Column(name = "usr_name")
    private String name;
    @Column(name = "usr_fst_lst")
    private String firstLastname;
    @Column(name = "usr_scn_lst")
    private String secondLastname;
    @Column(name="usr_addr")
    private String address;
    @Column(unique = true, name = "usr_email")
    private String email;
    @Column(name = "usr_pass")
    private String password;
    @Column(name = "usr_phone")
    private String phone;

    @Column(name = "usr_url_link")
    private String linkedin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recovery_tkn_id")
    private UserRecoveryToken recoveryToken;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "gen_id")
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_cv_id")
    private UserCV cv;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_ver_id")
    private UserVerification verification;

    @ManyToOne
    @JoinColumn(name = "usr_city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "pref_job_usr_id")
    private UserPreferredJob preferredJob;


    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ExamResult> results;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Tool> tools;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employment> jobs;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Academical> academicalList;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserJob> userJob;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private List<Role> roles;



}