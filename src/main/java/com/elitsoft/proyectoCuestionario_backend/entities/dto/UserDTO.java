package com.elitsoft.proyectoCuestionario_backend.entities.dto;


import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class UserDTO {

    private Long id;
    private String rut;
    private String name;
    private String firstlastname;
    private String secondLastname;
    private String address;
    private String email;
    private String phone;
    private String linkedin;
    private Gender gender;
    private UserCV cv;
    private City city;
    private UserPreferredJob preferredJob;
    private List<ExamResult> results;
    private List<Tool> tools;
    private List<Employment> jobs;
    private List<Academical> academicalList;
    private List<UserJob> userJob;


}
