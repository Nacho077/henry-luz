package com.henry.luz.receptor.service;

import com.henry.luz.receptor.model.*;
import com.henry.luz.receptor.repository.FacturaRepository;
import com.henry.luz.receptor.utils.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private MedicionesService medicionesService;

    public String generarFactura(Integer id, Integer month) {
        Cliente cliente = clienteService.getById(id);

        if(Objects.isNull(cliente.getMedidor())){
            throw new MissingResourceException("el cliente no posee medidor", "cliente", "");
        }

        //Traer mediciones y setear fechas
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        Date dateF = calendar.getTime();
        calendar.set(Calendar.MONTH, month - 1);
        Date dateI = calendar.getTime();
        List<MedicionDTO> mediciones = medicionesService.getByTime(cliente.getMedidor().getId(), dateI, dateF);
        MedicionDTO lastMedicion = mediciones.get(mediciones.size() - 1);
        MedicionDTO firstMedicion = mediciones.get(0);

        //setear tipo de factura
        Factura newFactura;

        if(cliente.getFacturaType().name().equals("MAYORES")){
            newFactura = new FacturaMayores();
        }else if(cliente.getFacturaType().name().equals("ANTIGUEDAD")){
            newFactura = new FacturaAntiguedad();
        }else{
            newFactura = new FacturaComun();
        }

        //agregar datos
        newFactura.setNumeroDeMedidor(cliente.getMedidor().getId());
        newFactura.setTipoDeTarifa(cliente.getFacturaType().name());
        newFactura.setCliente(cliente);
        newFactura.setDateMedicionInicial(firstMedicion.getFecha());
        newFactura.setDateMedicionFinal(lastMedicion.getFecha());
        newFactura.setMedicionInicial(firstMedicion.getMedicion());
        newFactura.setMedicionFinal(lastMedicion.getMedicion());
        newFactura.setConsumoTotal(lastMedicion.getMedicion() - firstMedicion.getMedicion());
        newFactura.setTotalPagar(newFactura.getConsumoTotal() * cliente.getFacturaType().getPrecio());
        newFactura.setPagado(false);

        facturaRepository.save(newFactura);

        return URLBuilder.buildURL("factura", newFactura.getId());
    }

    public Factura getById(Integer id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Factura> getByClient(Integer id){
        return facturaRepository.findAllByClienteId(id);
    }

    public String pagar(Integer id) {
        Factura factura = this.getById(id);
        factura.setPagado(true);
        facturaRepository.save(factura);
        return URLBuilder.buildURL("factura", factura.getId());
    }
}
