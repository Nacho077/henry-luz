package com.henry.luz.medidor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.luz.medidor.model.Medicion;
import com.henry.luz.medidor.model.Medidor;
import com.henry.luz.medidor.repository.MedidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MedicionService {

    @Autowired
    MedidorRepository medicionRepository;

    @Scheduled(fixedRate = 100)
    public void sendMedicion() throws IOException, InterruptedException {

        Medicion medicion = this.createMedicion();

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(medicion);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/medicion/medidor/1"))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private Medicion createMedicion() throws IOException, InterruptedException {
        List<Medicion> lista = medicionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        Medicion nueva = new Medicion();

        if(lista.size() == 0){
            this.createMedidor();
            nueva.setFecha(new Date());
            nueva.setMedicion(0.5F);
        }else{
            Medicion ultima = lista.get(0);

            Calendar nuevaFecha = Calendar.getInstance();
            nuevaFecha.setTime(ultima.getFecha());
            nuevaFecha.add(Calendar.HOUR, 12);

            nueva.setFecha(nuevaFecha.getTime());
            nueva.setMedicion(ultima.getMedicion() + 0.5F);
        }

        return medicionRepository.save(nueva);
    }

    private void createMedidor() throws IOException, InterruptedException {
        Medidor medidor = new Medidor();
        medidor.setMarca("Stanley");
        medidor.setModelo("S-37");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(medidor);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/medidor"))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
