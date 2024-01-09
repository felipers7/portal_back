package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.ProductVersion;

import java.util.List;

public interface ProductVersionService {

    List<ProductVersion> findByProductoId(Long prod_id) throws Exception;
}
