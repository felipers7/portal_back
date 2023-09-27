package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Herramienta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import java.util.List;


/**
 *
 * @author Maeva Martínez
 */
public interface HerramientaService {
    
    Herramienta guardarHerramienta(Herramienta herramienta, Long usr_id) throws Exception;

    List<Herramienta> obtenerHerramientasPorUsuario(Usuario usuario);
    
    List<Herramienta> obtenerListaHerramientas();
    
    public Herramienta obtenerHerramienta(Long herr_usr_id)throws Exception ;
    
    List<Herramienta> obtenerHerramientasConProductosPorUsuario(Long usuarioId);
        
    
}
        
    

