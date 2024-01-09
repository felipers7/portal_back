
package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public interface ProductCategoryService {
    
    List<ProductCategory> listarCategorias(); //para obtener todas
    List<Product> findProductosByCategoriaId(Long categoriaId);
    
    
}
