package com.henry.luz.receptor.controller;

import com.henry.luz.receptor.model.Factura;
import com.henry.luz.receptor.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/cliente/{id}")
    private Factura generarFactura(@PathVariable Integer id){
        return facturaService.generarFactura(id);
    }
}
