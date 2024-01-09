
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.services.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maeva Martínez
 */
@RestController
@RequestMapping("/categoria-productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductCategoryController {
    
    @Autowired
    private final ProductCategoryService productCategoryService;

    
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/")
    public List<ProductCategory> listarCategorias() {
        return productCategoryService.listarCategorias();
    }

    @GetMapping("/{categoriaId}/productos")
    public List<Product> obtenerProductosPorCategoria(@PathVariable Long cat_prod_id) {
        return productCategoryService.findProductosByCategoriaId(cat_prod_id);
    }
    
    
}
