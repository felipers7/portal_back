package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.ProductVersion;
import com.elitsoft.proyectoCuestionario_backend.services.ProductVersionService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/versiones-producto")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ProductVersionController {

    @Autowired
    private final ProductVersionService productVersionService;

    @Autowired
    public ProductVersionController(ProductVersionService productVersionService) {
        this.productVersionService = productVersionService;
    }

    @GetMapping("/por-producto/{productoId}")
    public ResponseEntity<List<ProductVersion>> obtenerVersionesPorProducto(@PathVariable Long productoId) throws Exception {
        List<ProductVersion> versiones = productVersionService.findByProductoId(productoId);
        return ResponseEntity.ok(versiones);
    }
}
