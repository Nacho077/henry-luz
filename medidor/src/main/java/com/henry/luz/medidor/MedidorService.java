package com.henry.luz.medidor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;

@Service
public class MedidorService {

    @Scheduled(fixedRate = 1000)
    public void sendMedicion(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http:localhost:8080/medidor"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }
}
