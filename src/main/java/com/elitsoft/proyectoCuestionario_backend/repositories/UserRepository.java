
package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.User;

import java.util.List;

import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.entities.UserRecoveryToken;
import com.elitsoft.proyectoCuestionario_backend.entities.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maeva Martínez
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    Optional <User> findById(Long usr_id);

    Optional<User> findByRecoveryToken(UserRecoveryToken token);
    Optional<User> findByVerification(UserVerification verification);
    Optional<User> findByEmail(String email);

    List<User> findAll();


    //@Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.city WHERE u.usr_id = :id")
   // Usuario findByIdWithCity(@Param("id") Long id);





}
