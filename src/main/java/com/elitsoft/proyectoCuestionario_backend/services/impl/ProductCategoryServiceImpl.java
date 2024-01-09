
package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductCategoryRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    
    @Autowired
    private final ProductCategoryRepository productCategoryRepository;

    
    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> listarCategorias() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<Product> findProductosByCategoriaId(Long categoriaId) {
         return productCategoryRepository.findProductosByCategoriaId(categoriaId);
    }
    
}
