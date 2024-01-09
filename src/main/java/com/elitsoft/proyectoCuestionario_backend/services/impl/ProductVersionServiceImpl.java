package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.ProductVersion;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.services.ProductVersionService;

import java.util.List;

@Service
public class ProductVersionServiceImpl implements ProductVersionService {

    private final ProductRepository productRepository;
    private final ProductVersionRepository productVersionRepository;

    @Autowired
    public ProductVersionServiceImpl(ProductRepository productRepository, ProductVersionRepository productVersionRepository) {
        this.productRepository = productRepository;
        this.productVersionRepository = productVersionRepository;
    }

    @Override
    public List<ProductVersion> findByProductoId(Long productId) throws Exception{
        return productVersionRepository.findOfficialVersionsByProduct(productId);
    }
}
