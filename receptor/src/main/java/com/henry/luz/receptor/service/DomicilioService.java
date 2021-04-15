package com.henry.luz.receptor.service;

import com.henry.luz.receptor.model.Domicilio;
import com.henry.luz.receptor.model.Medidor;
import com.henry.luz.receptor.repository.DomicilioRepository;
import com.henry.luz.receptor.utils.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private MedidorService medidorService;

    public List<Domicilio> getAll() {
        return domicilioRepository.findAll();
    }

    public String addDomicilio(Domicilio domicilio) {
        Domicilio newDomicilio = domicilioRepository.save(domicilio);
        return URLBuilder.buildURL("domicilio", newDomicilio.getId());
    }

    public Domicilio getById(Integer id) {
        return domicilioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public String addMedidor(Integer id, Integer idMedidor) {
        Domicilio domicilio = this.getById(id);
        Medidor medidor = medidorService.getById(idMedidor);
        domicilio.setMedidor(medidor);
        domicilioRepository.save(domicilio);
        return URLBuilder.buildURL("domicilio", id);
    }
}
