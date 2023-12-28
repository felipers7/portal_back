
package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.Exam;
import com.elitsoft.proyectoCuestionario_backend.entities.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martínez
 */
public interface QuestionRepository extends JpaRepository <Question, Long>{
    
    List<Question> findByExam(Exam exam);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_qst WHERE qst_id = :id", nativeQuery = true)
    void eliminarPregunta(@Param("id") Long id);

}