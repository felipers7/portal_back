package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.services.ProductService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Maeva Martinez
 */
@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint para obtener todos los productos
    @GetMapping("/")
    public ResponseEntity<List<Product>> obtenerTodosLosProductos() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    // Endpoint para obtener los productos por categoría
    @GetMapping("/producto-por-categoria/{categoryId}")
    public ResponseEntity<List<Product>> obtenerProductosPorCategoria(@PathVariable Long categoryId) {
        List<Product> products = productService.findByCategoriaId(categoryId);
        return ResponseEntity.ok(products);
    }
    
    // Endpoint para obtener el nombre del producto por su ID
    @GetMapping("/nombre-producto/{productoId}")
    public ResponseEntity<String> obtenerNombreProducto(@PathVariable Long productoId) {
        String nombreProducto = productService.obtenerNombreProducto(productoId);
        return ResponseEntity.ok(nombreProducto);
    }


    @PostMapping("/")
    public Boolean guardar_producto (@RequestBody Product product)  {
        productService.guardar_producto(product);
        return  true;
    }

    @DeleteMapping("/{producto}")
    public Boolean remove_producto(@PathVariable Long producto){
        productService.remove_producto(producto);
        return true;
    }
}
