
package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Maeva Martínez
 */
public interface ProductCategoryRepository extends JpaRepository <ProductCategory, Long> {
    
    //List<Categoria_Producto> findByProductos(Producto producto);
    // Método para obtener todos los productos asociados a una categoría específica
    @Query("SELECT p FROM Product p WHERE p.productCategory.id = :cat_prod_id")
    List<Product> findProductosByCategoriaId(@Param("cat_prod_id") Long cat_prod_id);
    
    // Método para obtener todas las categorías de productos
    List<ProductCategory> findAll();
    
     
    
}
