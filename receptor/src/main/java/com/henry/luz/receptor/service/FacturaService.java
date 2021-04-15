package com.henry.luz.receptor.service;

import com.henry.luz.receptor.model.*;
import com.henry.luz.receptor.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.MissingResourceException;
import java.util.Objects;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteService clienteService;

    public Factura generarFactura(Integer id) {
        Cliente cliente = clienteService.getById(id);
        if(Objects.isNull(cliente.getDomicilio())){
            throw new MissingResourceException("el cliente no posee domiclio", "cliente", "");
        }
        if(Objects.isNull(cliente.getDomicilio().getMedidor())){
            throw new MissingResourceException("el cliente no posee medidor", "cliente", "");
        }
        Factura newFactura;
        if(cliente.getFacturaType().equals("COMUN")){
            newFactura = new FacturaComun();
        }else if(cliente.getFacturaType().equals("ANTIGUEDAD")){
            newFactura = new FacturaAntiguedad();
        }else{
            newFactura = new FacturaMayores();
        }
        newFactura.setNumeroDeMedidor(cliente.getDomicilio().getMedidor().getId());
        newFactura.setTipoDeTarifa(cliente.getFacturaType().name());
        facturaRepository.save(newFactura);
        return newFactura;
    }
}
