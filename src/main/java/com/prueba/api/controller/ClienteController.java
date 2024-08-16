package com.prueba.api.controller;

import com.prueba.api.model.Dto.ClienteDto;
import com.prueba.api.model.entity.Cliente;
import com.prueba.api.model.payload.MensajeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prueba.api.service.IClienteService;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        return clienteService.save(clienteDto);
    }

    @PutMapping("cliente")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto) {
        return clienteService.update(clienteDto);
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return clienteService.delete(id);
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Integer id) {
        return clienteService.findById(id);
    }

    @GetMapping("clientes")
    public ResponseEntity<?> listadoClientes() {
        return clienteService.buscarAll();
    }
}
