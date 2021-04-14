package com.henry.luz.receptor.controller;

import com.henry.luz.receptor.model.Medidor;
import com.henry.luz.receptor.service.MedidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medidor")
public class MedidorController {

    @Autowired
    private MedidorService medidorService;

    @GetMapping
    public List<Medidor> getMedidores(){
        return medidorService.getAll();
    }

    @PostMapping
    public String addMedidor(@RequestBody Medidor medidor){
        return medidorService.addMedidor(medidor);
    }

    @GetMapping("/{id}")
    public Medidor getMedidorById(@PathVariable Integer id){
        return medidorService.getById(id);
    }
}
