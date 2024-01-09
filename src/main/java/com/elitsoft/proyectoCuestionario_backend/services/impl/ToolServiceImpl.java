package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.ProductCategory;
import com.elitsoft.proyectoCuestionario_backend.entities.Tool;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CreateToolDTO;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.ProductVersionRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.ToolRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ToolService;

import java.util.*;

import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martinez
 */

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductVersionRepository productVersionRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;


    @Override
    public Boolean deleteUserTool(Long toolId, String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null){
            return false;
        }
        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()){
            return false;
        }
        //TODO check that user is tool's owner
        toolRepository.deleteById(toolId);
        return true;
    }

    @Override
    public Boolean createTool(CreateToolDTO toolDTO, String Jwt) throws Exception {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(Jwt);
        if (token == null){
            return false;
        }

        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()){
            return false;
        }

        Tool newTool = new Tool();
        newTool.setUser(usuarioOpt.get());
        newTool.setLevel(toolDTO.getLevel());
        newTool.setYearsOfExperience(toolDTO.getYearsOfExperience());

        System.out.println("incoming tool "+ toolDTO);


        //Categoría será siempre desde base de datos, fija
        ProductCategory category = new ProductCategory();
        category.setId(toolDTO.getCategoryId());
        toolDTO.getProduct().setProductCategory(category);

        if(toolDTO.getProduct().getId() == 0){
            toolDTO.getProduct().setId(null);
            toolDTO.setProduct(productRepository.save(toolDTO.getProduct()));
        }

        if(toolDTO.getVersion().getId() == 0){
            toolDTO.getVersion().setId(null);
            toolDTO.getVersion().setProduct(toolDTO.getProduct());
            toolDTO.setVersion(productVersionRepository.save(toolDTO.getVersion()));
        }
        newTool.setProductVersion(toolDTO.getVersion());
        newTool.getProductVersion().setProduct(toolDTO.getProduct());


        toolRepository.save(newTool);
        return true;
    }



    @Override
    public List<Tool> obtenerListaHerramientasPorUsuario(String jwt) {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Tool> tools = toolRepository.findByUser(userOptional.get());
        if(tools == null){
            return Collections.emptyList();
        }

        return tools;
    }
    
    @Override
    public Tool obtenerHerramienta(Long herr_usr_id) {
        return toolRepository.findById(herr_usr_id).orElse(null);
    }
    
    
    @Override
    public List<Tool> obtenerHerramientasConProductosPorUsuario(Long usuarioId) {
        return toolRepository.obtenerHerramientasConProductosPorUsuario(usuarioId);
    }

    @Override
    public void eliminarHerramientaPorUsuario(Long usuarioId) {
        Optional<User> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Tool> tools = toolRepository.findByUser(usuario.get());
            toolRepository.deleteAll(tools);
        }
    }

   
}


