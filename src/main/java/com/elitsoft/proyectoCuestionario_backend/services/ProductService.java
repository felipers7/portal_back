
package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface ProductService {
    
    List<Product> findByCategoriaId(Long cat_prod_id);
    
    List<Product> findAll();
    
    // Método para obtener el nombre del producto por su ID
    String obtenerNombreProducto(Long prdId);


    public Boolean guardar_producto(Product product);

    public Boolean remove_producto(Long producto);
}
