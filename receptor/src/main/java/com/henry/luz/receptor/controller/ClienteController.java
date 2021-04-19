package com.henry.luz.receptor.controller;

import com.henry.luz.receptor.model.Cliente;
import com.henry.luz.receptor.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Alta de cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alta de cliente exitosa",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST, Verificar JSON",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
    })
    public String addCliente(@RequestBody Cliente cliente){
        return clienteService.addCliente(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clienteService.getById(id);
    }

    @PostMapping("/{id}/medidor/{idMedidor}")
    public String addDomicilioToCliente(@PathVariable Integer id, @PathVariable Integer idMedidor){
        return clienteService.addMedidor(id, idMedidor);
    }
}
