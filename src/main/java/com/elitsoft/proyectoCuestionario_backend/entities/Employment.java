package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */

@Entity
@Table(name = "TBL_INF_EMP")
@Data
public class Employment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inf_emp_id")
    private Long id;
    @Column(name = "inf_emp_job_emp")
    private String position;
    @Column(name = "inf_emp_comp")
    private String company;
    @Column(name = "inf_emp_act")
    private String activities;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "inf_emp_start_date")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "inf_emp_end_date")
    private Date endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inf_emp_id")
    private List<EmploymentReference> employmentReferences;

    @ManyToMany
    @JoinTable(name = "tbl_tools_emp",
            joinColumns = @JoinColumn(name = "inf_emp_id"),
            inverseJoinColumns = @JoinColumn(name = "tool_usr_id"))
    private List<Tool> tools;
    
}
