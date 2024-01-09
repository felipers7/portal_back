
package com.elitsoft.proyectoCuestionario_backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */

@Entity
@Table(name = "TBL_PRD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prd_id")
    private Long id;
    @Column(name = "prd_nom")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_prod_id")
    private ProductCategory productCategory;


    
}
