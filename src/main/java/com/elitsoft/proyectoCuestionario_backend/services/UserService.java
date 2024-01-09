
package com.elitsoft.proyectoCuestionario_backend.services;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;



/**
 *
 * @author Maeva Martínez
 */
@Service
public interface UserService {
    
    public User guardarUsuario(User user, Long Id) throws Exception;

    public User obtenerUsuarioId(Long usr_id)throws Exception ;

    public Boolean verificarUsuario(Map<String,String> body);
    public User getUsuarioByEmail(String email);

    public Optional<User> getUsuarioByToken(String jwt);
    public void pedirRestaurarPassword(User user) throws MessagingException, UnsupportedEncodingException;
    public Boolean cambiarPassword(String code, String password);

    void uploadUserCv(String jwt, MultipartFile cv) throws IOException;

    public Boolean updateUser(User user, String jwt);

    public Resource getCVByUser(Long userId) throws IOException, EntityNotFoundException;

    User actualizarUsuarioId(Long usr_id, User user);

    public List<User> listarUsuarios();

    User obtenerDatosUsuario(String jwt) throws Exception;

    public  List<User> obtenerUsuario();

    public List<User> listarUsuariosConHerramientas();

    void eliminarUsuarioId (Long usr_id);

    public void eliminarCV(Long usuarioId) throws IOException, EntityNotFoundException;

    public void eliminarCVByUserId (Long userId) throws IOException;

    public void deleteFile(String filePath);

    public void eliminarCVByUser(Long userId) ;

}
