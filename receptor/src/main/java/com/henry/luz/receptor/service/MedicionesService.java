package com.henry.luz.receptor.service;

import com.henry.luz.receptor.converter.MedicionListToDTO;
import com.henry.luz.receptor.model.MedicionDTO;
import com.henry.luz.receptor.model.Mediciones;
import com.henry.luz.receptor.model.Medidor;
import com.henry.luz.receptor.repository.MedicionesRepository;
import com.henry.luz.receptor.utils.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Objects;

@Service
public class MedicionesService {

    @Autowired
    private MedicionesRepository medicionesRepository;
    @Autowired
    private MedidorService medidorService;
    @Autowired
    private MedicionListToDTO medicionListToDTO;

    public List<Mediciones> getAll() {
        return medicionesRepository.findAll();
    }

    public List<Mediciones> getAllOfMedidor(Integer id) {
        return medicionesRepository.findByMedidorId(id);
    }

    public Mediciones getById(Integer id) {
        return medicionesRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public String addMedicion(Mediciones medicion, Integer id) {
        Medidor medidor = medidorService.getById(id);
        medicion.setMedidor(medidor);
        medicionesRepository.save(medicion);
        return URLBuilder.buildURL("medicion", medicion.getId());
    }

    public List<MedicionDTO> getByTime(Integer id, Date dateI, Date dateF) {
        return medicionListToDTO.convert(medicionesRepository.findAllByMedidorIdAndFechaBetween(id, dateI, dateF));
    }
}
