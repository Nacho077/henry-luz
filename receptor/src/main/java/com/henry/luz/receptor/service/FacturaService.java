package com.henry.luz.receptor.service;

import com.henry.luz.receptor.model.Factura;
import com.henry.luz.receptor.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura generarFactura(Integer id) {
        Factura newFactura = facturaRepository.save(new Factura());
        return newFactura;
    }
}
