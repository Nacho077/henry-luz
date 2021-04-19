package com.henry.luz.receptor.service;

import com.henry.luz.receptor.model.Cliente;
import com.henry.luz.receptor.model.Medidor;
import com.henry.luz.receptor.repository.ClienteRepository;
import com.henry.luz.receptor.utils.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MedidorService medidorService;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public String addCliente(Cliente cliente) {
        Cliente newClient = clienteRepository.save(cliente);
        return URLBuilder.buildURL("cliente", newClient.getId());
    }

    public Cliente getById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public String addMedidor(Integer id, Integer idMedidor) {
        Cliente cliente = this.getById(id);
        Medidor Medidor = medidorService.getById(idMedidor);
        cliente.setMedidor(Medidor);
        clienteRepository.save(cliente);
        return URLBuilder.buildURL("cliente", id);
    }
}