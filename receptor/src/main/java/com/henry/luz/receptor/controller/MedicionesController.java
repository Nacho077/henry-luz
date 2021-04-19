package com.henry.luz.receptor.controller;

import com.henry.luz.receptor.converter.MedicionListToDTO;
import com.henry.luz.receptor.model.MedicionDTO;
import com.henry.luz.receptor.model.Mediciones;
import com.henry.luz.receptor.service.MedicionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicion")
public class MedicionesController {

    @Autowired
    private MedicionesService medicionesService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MedicionListToDTO medicionListToDTO;

    @GetMapping
    public List<Mediciones> getAllMedidas(){
        return medicionesService.getAll();
    }

    @GetMapping("/{id}")
    public Mediciones getById(@PathVariable Integer id){
        return medicionesService.getById(id);
    }

    @GetMapping("/medidor/{id}")
    public List<MedicionDTO> getAllOfMedidor(@PathVariable Integer id){
        return medicionListToDTO.convert(medicionesService.getAllOfMedidor(id));
    }

    @PostMapping("/medidor/{id}")
    public String addMedicion(@PathVariable Integer id, @RequestBody Mediciones medicion){
        return medicionesService.addMedicion(medicion, id);
    }
}