package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Tool;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CreateToolDTO;

import java.util.List;


/**
 *
 * @author Maeva Martínez
 */
public interface ToolService {

    Boolean deleteUserTool(Long toolId, String jwt);
    Boolean createTool(CreateToolDTO tool, String Jwt) throws Exception;

    List<Tool> obtenerListaHerramientasPorUsuario(String jwt);

    public Tool obtenerHerramienta(Long herr_usr_id)throws Exception ;
    
    List<Tool> obtenerHerramientasConProductosPorUsuario(Long usuarioId);

    void eliminarHerramientaPorUsuario(Long usuarioId);
        
    
}
        
    

