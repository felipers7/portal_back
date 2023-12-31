
package com.elitsoft.proyectoCuestionario_backend.repositorios;


import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Maeva Martínez
 */
public interface HerramientaRepository extends JpaRepository <Herramienta, Long>{
    
    // Método para guardar una herramienta asociada a un usuario
    Herramienta save(Herramienta herramienta);

    // Método para obtener herramientas por usuario
    List<Herramienta> findByUsuario(Usuario usuario);

    // Método para listar todas las herramientas
    List<Herramienta> findAll();
    
    //Metodo para obtener una herramienta por su id
    Optional <Herramienta> findById(Long herr_usr_id);
    
    
    
    @Query("SELECT h FROM Herramienta h " +
       "JOIN FETCH h.producto p " +
       "WHERE h.usuario.usr_id = :usuarioId")
    List<Herramienta> obtenerHerramientasConProductosPorUsuario(@Param("usuarioId") Long usuarioId);
    
    
}
