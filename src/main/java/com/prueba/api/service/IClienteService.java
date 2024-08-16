package com.prueba.api.service;

import com.prueba.api.model.Dto.ClienteDto;
import com.prueba.api.model.entity.Cliente;
import org.springframework.http.ResponseEntity;


public interface IClienteService {

    ResponseEntity<?> save(ClienteDto clienteDto);

    ResponseEntity<?> update(ClienteDto clienteDto);

    ResponseEntity<?> findById (Integer id);

    ResponseEntity<?> buscarAll ();

    ResponseEntity<?> delete(Integer id);

}
