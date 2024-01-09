
package com.elitsoft.proyectoCuestionario_backend.repositories;


import com.elitsoft.proyectoCuestionario_backend.entities.Tool;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martínez
 */
public interface ToolRepository extends JpaRepository <Tool, Long>{
    
    // Método para guardar una herramienta asociada a un usuario
    Tool save(Tool tool);

    // Método para obtener herramientas por usuario
    List<Tool> findByUser(User user);

    // Método para listar todas las herramientas
    List<Tool> findAll();
    
    //Metodo para obtener una herramienta por su id
    Optional <Tool> findById(Long herr_usr_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_tools_emp WHERE tool_usr_id = :id", nativeQuery = true)
    void deleteLaboralHerramientaReferences(@Param("id") Long id);




    @Query("SELECT h FROM Tool h " +
       "WHERE h.user.id = :usuarioId")
    List<Tool> obtenerHerramientasConProductosPorUsuario(@Param("usuarioId") Long usuarioId);

    
}
