
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.exceptions.CustomError;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.services.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Elitsoft83
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private AcademicalService academicalService;

    @Autowired
    private ExamResultService examResultService;

    @Autowired
    private UserJobService userJobService;

    @Autowired
    private EmploymentService employmentService;

    @Autowired
    private ToolService toolService;

    @GetMapping("/all")
    public List<User> obtenerUsuarios(){
        return userService.obtenerUsuario();
    }


    @PostMapping("/")
    public ResponseEntity<?> guardarUsuario(@RequestBody User user, Long cityId) throws Exception{
        try{
            userService.guardarUsuario(user, cityId);
        }
        catch (DataAccessException ex){
            CustomError error = new CustomError();
            error.setError("El usuario ya existe.");
            return new ResponseEntity<CustomError>(error, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(true ,HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestHeader("Authorization") String Jwt){
        try {
            userService.updateUser(user, Jwt);
            System.out.println(user);
        }
        catch (DataAccessException ex){
            return new ResponseEntity<>(ex.getMessage() ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true ,HttpStatus.OK);
    }

    @GetMapping("/file/{userId}")
    public ResponseEntity<?> getUserFile(@PathVariable("userId") Long userId){
        try{
            Resource cv = userService.getCVByUser(userId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<Resource>(cv, headers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (IOException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/file")
    public ResponseEntity<?> actualizarUsuarioFile(@RequestParam("file") MultipartFile file,
                                               @RequestHeader("Authorization") String jwt){
        try {
            userService.uploadUserCv(jwt,file);
        }
        catch (DataAccessException | IOException ex){
            return new ResponseEntity<>(ex.getMessage() ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true ,HttpStatus.OK);
    }

    @GetMapping("/")
    public User obtenerUsuario(@RequestHeader("Authorization") String jwt)throws Exception{
        return userService.obtenerDatosUsuario(jwt);
    }

    @PostMapping("/verificar")
    public ResponseEntity<Boolean> verificarUsuario(@RequestBody Map<String, String> requestData){
        if(userService.verificarUsuario(requestData)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/pedir-restauracion-pass")
    public void pedirRestauracionPassword(@RequestBody User user) throws MessagingException, UnsupportedEncodingException {
      userService.pedirRestaurarPassword(user);
    }
    @PutMapping("/cambiar-password/{code}")
    public Boolean cambiarPassword(@PathVariable("code") String rec_code,@RequestBody Map<String,String> password){

        return userService.cambiarPassword(rec_code, password.get("pass"));
    }

    @GetMapping("/usuarios-herramientas")
    public List<User> listarUsuariosConHerramientas(){
        List<User> usuariosConHerramientas = userService.listarUsuariosConHerramientas();
        return usuariosConHerramientas;
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<Map<String, String>> eliminarUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
        Map<String, String> response = new HashMap<>();

        try {
            examResultService.eliminarResultadosPorUsuario(usuarioId);
            userJobService.eliminarCargoPorUsuario(usuarioId);
            employmentService.eliminarLaboralPorUsuario(usuarioId);
            toolService.eliminarHerramientaPorUsuario(usuarioId);
            academicalService.eliminarAcademicasPorUsuario(usuarioId);
            userService.eliminarUsuarioId(usuarioId);

            response.put("message", "Usuario eliminado con éxito.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (AccessDeniedException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }


    @PutMapping("/actualizar/{usuarioId}")
    public ResponseEntity<User> actualizarUsuarioId(@PathVariable Long usuarioId,
                                                    @RequestBody User user){
        User userActualizado = userService.actualizarUsuarioId(usuarioId, user);
        return ResponseEntity.ok(userActualizado);
    }


    @GetMapping("/lista-usuarios")
    public ResponseEntity<List<User>> listarUsuarios(){
        List<User> users = userService.listarUsuarios();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/email/{userEmail}")
    public ResponseEntity<User> getUsuarioByEmail(@PathVariable String userEmail){
        User user = userService.getUsuarioByEmail(userEmail);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/{usuarioId}")
    public User obtenerUsuarioId(@PathVariable Long usuarioId) throws Exception{
        return userService.obtenerUsuarioId(usuarioId);
    }



}
