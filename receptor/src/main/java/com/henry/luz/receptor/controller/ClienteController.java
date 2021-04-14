package com.henry.luz.receptor.controller;

import com.henry.luz.receptor.model.Cliente;
import com.henry.luz.receptor.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteService.getAll();
    }

    @PostMapping
    public String addCliente(@RequestBody Cliente cliente){
        return clienteService.addCliente(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clienteService.getById(id);
    }
}
