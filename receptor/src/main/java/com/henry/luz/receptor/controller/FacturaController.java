package com.henry.luz.receptor.controller;

import com.henry.luz.receptor.model.Factura;
import com.henry.luz.receptor.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/{id}")
    private Factura getFacturaById(@PathVariable Integer id){
        return facturaService.getById(id);
    }

    @GetMapping("/cliente/{id}")
    private List<Factura> getByClientId(@PathVariable Integer id){
        return facturaService.getByClient(id);
    }

    @PostMapping("/cliente/{id}/{month}")
    private String generarFactura(@PathVariable Integer id, @PathVariable Integer month){
        return facturaService.generarFactura(id, month);
    }

    @PutMapping("/{id}")
    private String PagarFactura(@PathVariable Integer id){
        return facturaService.pagar(id);
    }
}
