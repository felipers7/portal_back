
package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.repositories.CityRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.FileService;
import com.elitsoft.proyectoCuestionario_backend.services.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FileService fileService;

    @Override
    public User guardarUsuario(User user, Long cityId) throws Exception {
        Long usrId = user.getId();

        if (usrId != null) {
            Optional<User> usuarioLocal = userRepository.findById(usrId);
            if (usuarioLocal.isPresent()) {
                System.out.println("El usuario ya existe");
                throw new Exception("El usuario ya está presente");
            }
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        //TOdo user.setVerificationToken(UUID.randomUUID().toString());

        //TOdo user.setIsVerified(false);
        //TOdo  user.setRol("GUEST");

        User nuevoUser = userRepository.save(user);
        emailService.sendVerificationEmail(nuevoUser);

        return nuevoUser;
    }

    @Override
    public Boolean verificarUsuario(Map<String, String> body) {

        Optional<User> user = userRepository.findByVerification(new UserVerification());

        if (!user.isPresent()) {
            return false;
        }

        User presentUser = user.get();
        //TODO presentUser.setIsVerified(true);

        User savedUser = userRepository.save(presentUser);

        return false;
    }

    @Override
    public User getUsuarioByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseGet(User::new);
        user.setPassword("");
        //TODO user.setRecoveryToken("");
        // user.setVerificationToken("");
        return user;
    }


    @Override //aqui
    public User obtenerUsuarioId(Long usr_id) {
        return userRepository.findById(usr_id).orElse(null);
    }

    @Override
    public Boolean cambiarPassword(String code, String password) {

        if (code.isEmpty()) {
            return false;
        }

        //TODO
        Optional<User> usuario = userRepository.findByRecoveryToken(new UserRecoveryToken());
        if (!usuario.isPresent()) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        usuario.get().setPassword(encoder.encode(password));

        User savedUser = userRepository.save(usuario.get());
        return true;
    }

    @Override
    public void uploadUserCv(String jwt, MultipartFile cv) throws IOException {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return;
        }

        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());
        if (!usuarioOpt.isPresent()) {
            return;
        }
        String filePath = fileService.saveFile(cv);
        User user = usuarioOpt.get();
        //TODO user.setCvPath(filePath);
        userRepository.save(user);

    }

    @Override
    public void pedirRestaurarPassword(User userEntrante) throws MessagingException, UnsupportedEncodingException {

        String email = userEntrante.getEmail();

        Optional<User> usuario = userRepository.findByEmail(email);
        if (!usuario.isPresent()) {
            return;
        }
        //usuario.get().setRecoveryToken(UUID.randomUUID().toString());

        User userActualizado = userRepository.save(usuario.get());

        if (!userActualizado.getEmail().equals(email)) {
            return;
        }
        emailService.sendRecoverPassword(userActualizado);
    }


    @Override
    public Boolean updateUser(User user, String jwt) {
        System.out.println("Iniciando actualización de usuario");

        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            System.out.println("Token JWT es nulo");
            return false;
        }

        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()) {
            System.out.println("Usuario no encontrado en la base de datos");
            return false;
        }

        User userInDatabase = usuarioOpt.get();

        userInDatabase.setSecondLastname(user.getSecondLastname());
        userInDatabase.setFirstLastname(user.getFirstLastname());
        userInDatabase.setName(user.getName());
        userInDatabase.setRut(user.getRut());
        userInDatabase.setPhone(user.getPhone());
        userInDatabase.setCity(user.getCity());
        userInDatabase.setAddress(user.getAddress());
        userInDatabase.setGender(user.getGender());

        System.out.println("this is the incoming gender: " + user.getGender());


        User userActualizado = userRepository.save(userInDatabase);
        System.out.println("Usuario actualizado con éxito");
        return true;
    }


    @Override
    public Resource getCVByUser(Long userId) throws IOException,
            EntityNotFoundException {


        Optional<User> usuario = userRepository.findById(userId);
        if (!usuario.isPresent()){
            throw  new EntityNotFoundException("No user with that id");
        }
        //TODO fileService.getCV(usuario.get().getCvPath())
        return null ;

    }

    public User actualizarUsuarioId(Long usuarioId, User user){
        User userExistente = userRepository.findById(usuarioId).orElseThrow(
                () -> new NoSuchElementException("El user con ID " + usuarioId + " no se encontro.")
        );



        userExistente.setSecondLastname(user.getSecondLastname());
        userExistente.setFirstLastname(user.getFirstLastname());
        userExistente.setName(user.getName());
        userExistente.setRut(user.getRut());
        userExistente.setPhone(user.getPhone());
        userExistente.setCity(user.getCity());
        userExistente.setAddress(user.getAddress());
        userExistente.setEmail(user.getEmail());
        userExistente.setPassword(user.getPassword());
        //TODO userExistente.setRol(user.getRol());

        return userRepository.save(userExistente);
    }

    public List<User> listarUsuarios(){
        return userRepository.findAll();
    }

    @Override
    public User obtenerDatosUsuario(String jwt) throws Exception {
        Optional<User> userOptional = getUsuarioByToken(jwt);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        User user = userOptional.get();



        // Limpiar datos sensibles
        user.setPassword("");
        //user.setVerificationToken("");
       //TODO user.setRecoveryToken("");
        user.setTools(new ArrayList<>());


        return user;
    }

    @Override
    public Optional<User> getUsuarioByToken(String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return Optional.empty();
        }
        return userRepository.findByEmail(token.getPrincipal().toString());
    }

    @Override
    public List<User> obtenerUsuario() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listarUsuariosConHerramientas() {
        return userRepository.findAll();
    }


    @Override
    public void eliminarUsuarioId(Long usuarioId) {

        User user = new User();
        user.setId(usuarioId);
        userRepository.delete(user);

    }



    @Override
    public void eliminarCVByUserId(Long userId) throws IOException {
        Optional<User> usuarioOpt = userRepository.findById(userId);
        if (usuarioOpt.isPresent()) {
            User user = usuarioOpt.get();
            String cvPath = " GET PATH";
            if (cvPath != null && !cvPath.isEmpty()) {
                fileService.deleteFile(cvPath); // Agregar lógica para eliminar el archivo
                //TODO user.setCvPath(null); // Establecer el campo del CV en null
                userRepository.save(user);
            }
        }
    }

    @Override
    public void deleteFile(String filePath) {
        // Agrega la lógica para eliminar el archivo en el sistema de archivos
        // Esto dependerá de cómo almacenas tus archivos, por ejemplo, usando java.io.File o algún otro enfoque.
    }

    @Override
    public void eliminarCVByUser(Long userId) {

    }

    @Override
    public void eliminarCV(Long usuarioId) throws IOException, EntityNotFoundException {
        Optional<User> usuarioOpt = userRepository.findById(usuarioId);
        if (!usuarioOpt.isPresent()) {
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        User user = usuarioOpt.get();
        //TODO String cvPath = user.getCvPath();
        String cvPath = "";
        if (cvPath != null && !cvPath.isEmpty()) {
            fileService.deleteFile(cvPath);
            //user.setCvPath(null);
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("El usuario no tiene un CV adjunto.");
        }
    }


}
