
package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductCategoryRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ProductService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }
    
   
    @Override
    public List<Product> findByCategoriaId(Long categoryId) {
        return productRepository.findOfficialProductsByCategory(categoryId);
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    @Override
    public String obtenerNombreProducto(Long prdId) {
        return productRepository.findNombreProductoById(prdId);
    }


    @Override
    public Boolean guardar_producto(Product product){
        productRepository.save(product);
        return true;
    }

    @Override
    public Boolean remove_producto(Long producto) {
        productRepository.deleteById(producto);
        return true;

    }


}
