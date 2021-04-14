package com.henry.luz.receptor.service;

import com.henry.luz.receptor.model.Medidor;
import com.henry.luz.receptor.repository.MedidorRepository;
import com.henry.luz.receptor.utils.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MedidorService {

    @Autowired
    private MedidorRepository medidorRepository;

    public List<Medidor> getAll() {
        return medidorRepository.findAll();
    }

    public String addMedidor(Medidor medidor) {
        Medidor newMedidor = medidorRepository.save(medidor);
        return URLBuilder.buildURL("medidor", newMedidor.getId());
    }

    public Medidor getById(Integer id) {
        return medidorRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
