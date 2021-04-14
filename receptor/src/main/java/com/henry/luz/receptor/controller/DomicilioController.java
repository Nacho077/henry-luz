package com.henry.luz.receptor.controller;

import com.henry.luz.receptor.model.Domicilio;
import com.henry.luz.receptor.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    @GetMapping
    public List<Domicilio> getDomicilios(){
        return domicilioService.getAll();
    }

    @PostMapping
    public String addDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.addDomicilio(domicilio);
    }

    @GetMapping("/{id}")
    public Domicilio getDomicilioById(@PathVariable Integer id){
        return domicilioService.getById(id);
    }
}
