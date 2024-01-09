package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.Level;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.Question;
import com.elitsoft.proyectoCuestionario_backend.repositories.QuestionRepository;
import com.elitsoft.proyectoCuestionario_backend.services.QuestionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question agregarPregunta(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question actualizarPregunta(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> obtenerPreguntas() {
        return (Set<Question>) questionRepository.findAll();
    }

    @Override
    public Question actualizarPreguntaId(Long preguntaId, Question question) {
        Question questionExistente = questionRepository.findById(preguntaId).orElseThrow(
                () -> new NoSuchElementException("La pregunta con ID " + preguntaId + " no se encontro.")
        );

        questionExistente.setContenido(question.getContenido());
        questionExistente.setOption1(question.getOption1());
        questionExistente.setOption2(question.getOption2());
        questionExistente.setOption3(question.getOption3());
        questionExistente.setOption4(question.getOption4());
        questionExistente.setAnswer(question.getAnswer());

        return questionRepository.save(questionExistente);
    }

    @Override
    public Question obtenerPregunta(Long preguntaId) {
        return questionRepository.findById(preguntaId).orElse(null);
    }

    @Override
    public void eliminarPregunta(Long preguntaId) {
        questionRepository.deleteById(preguntaId);
    }

    @Override
    public Question listarPregunta(Long preguntaId) {
        return questionRepository.getOne(preguntaId);
    }

    @Override
    public List<Question> generarExamen(String description, Long productId) {
        Product product = new Product();
        product.setId(productId);
        Level level = new Level();


        int totalQuestions = 10;
        int hardQuestionsCount = 0;
        int mediumQuestionsCount = 0;
        int easyQuestionsCount = 0;
        if ("alto".equals(description)) {
            hardQuestionsCount = (int) Math.round(totalQuestions * 0.7);
            level.setId(1L);
            mediumQuestionsCount = (int) Math.round(totalQuestions * 0.2);
            easyQuestionsCount = totalQuestions - hardQuestionsCount - mediumQuestionsCount;
        } else if ("medio".equals(description)) {
            mediumQuestionsCount = (int) Math.round(totalQuestions * 0.8);
            level.setId(2L);
            easyQuestionsCount = totalQuestions - mediumQuestionsCount;
        } else if ("bajo".equals(description)) {
            easyQuestionsCount = totalQuestions;
            level.setId(3L);
        }
        List<Question> allQuestions = new ArrayList<>(questionRepository.findByLevelAndProduct(level, product));
        List<Question> hardQuestions = getRandomQuestions(allQuestions, hardQuestionsCount);
        List<Question> mediumQuestions = getRandomQuestions(allQuestions, mediumQuestionsCount);
        List<Question> easyQuestions = getRandomQuestions(allQuestions, easyQuestionsCount);
        List<Question> examQuestions = new ArrayList<>();
        examQuestions.addAll(hardQuestions);
        examQuestions.addAll(mediumQuestions);
        examQuestions.addAll(easyQuestions);
        return examQuestions;
    }
    private List<Question> getRandomQuestions(List<Question> allQuestions, int count) {
        Collections.shuffle(allQuestions);
        return allQuestions.stream().limit(count).collect(Collectors.toList());
    }
    public List<Question> obtenerPreguntasPorProducto(Long productoId) {
        return questionRepository.findByProduct(productoId);
    }

}
