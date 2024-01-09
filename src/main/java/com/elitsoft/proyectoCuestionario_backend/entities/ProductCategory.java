
package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_CAT_PROD")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_prod_id")
    private Long id;
    @Column(name = "cat_prod_nom")
    private String name;


    
}
