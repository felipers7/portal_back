
package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.Country;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import com.elitsoft.proyectoCuestionario_backend.services.CountryService;
import com.elitsoft.proyectoCuestionario_backend.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Maeva Martínez 
 */
@RestController
@RequestMapping("/country")
@CrossOrigin("*")
public class CountryController {
    
    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @GetMapping("/")
    public List<Country> obtenerPaises(){
        return countryService.obtenerPaises();
    }

    @GetMapping("/{id}")
    public Country obtenerPaisId(@PathVariable("id") Long id){
        return countryService.obtenerPaisId(id);
    }

    /*@GetMapping("/{countryId}/states")
    public List<State> obtenerEstadosPorPais(@PathVariable("countryId") Long countryId) {
        return countryService.obtenerEstadosPorPais(countryId);
    }*/

    @GetMapping("/{countryId}/states")
    public ResponseEntity<List<State>> getStatesByCountry(@PathVariable Long countryId) {
        List<State> states = countryService.getStatesByCountry(countryId);
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    
}
