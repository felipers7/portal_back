package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.entities.City;
import com.elitsoft.proyectoCuestionario_backend.entities.State;
import com.elitsoft.proyectoCuestionario_backend.services.CityService;
import com.elitsoft.proyectoCuestionario_backend.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@CrossOrigin("*")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public List<State> obtenerEstados() {
        return stateService.obtenerEstados();
    }

    @GetMapping("/{id}")
    public State obtenerEstadoId(@PathVariable("id") Long Id) {
        return stateService.obtenerEstadoId(Id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<State> guardarEstado(@RequestBody State state ){


        try {
            stateService.guardarEstado(state);
            System.out.println(state);
        }
        catch (DataAccessException ex){
            return new ResponseEntity<State>(state, HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(state, HttpStatus.OK);
    }


    @GetMapping("/country/{Id}")
    public  List<State> obtenerEstadosporCountry(@PathVariable("Id") Long countryId){
        return stateService.estadosbyCountryId(countryId);
    }

    @GetMapping("/{stateId}/cities")
    public ResponseEntity<List<City>> getCitiesByState(@PathVariable Long stateId) {
        List<City> cities = stateService.getCitiesByState(stateId);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


}
