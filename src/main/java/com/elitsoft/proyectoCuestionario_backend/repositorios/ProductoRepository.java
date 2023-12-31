
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria_Producto;
import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Maeva Martínez
 */
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
    // Método para obtener productos filtrados por categoría
    @Query("SELECT p FROM Producto p WHERE p.cat_prod_id.id = :cat_prod_id")
    List<Producto> findByCat_prod_id_Id(@Param("cat_prod_id") Long cat_prod_id);
     
    
    // Método para obtener todos los productos disponibles
    List<Producto> findAll();
    
    // Método para obtener el nombre del producto por su ID
    @Query("SELECT p.prd_nom FROM Producto p WHERE p.prd_id = :prdId")
    String findNombreProductoById(@Param("prdId") Long prdId);
    
}
