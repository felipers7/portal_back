package com.elitsoft.proyectoCuestionario_backend.entities.dto;


import com.elitsoft.proyectoCuestionario_backend.entities.Level;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.ProductVersion;
import lombok.Data;

@Data
public class CreateToolDTO {

    private Integer yearsOfExperience;
    private Level level;
    private Product product;
    private ProductVersion version;
    private Long categoryId;
}
