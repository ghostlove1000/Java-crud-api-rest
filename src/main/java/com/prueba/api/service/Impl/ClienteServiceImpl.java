package com.prueba.api.service.Impl;

import com.prueba.api.model.mappings.Mappings;
import com.prueba.api.model.payload.MensajeResponse;
import com.prueba.api.repository.Dao.ClienteDao;
import com.prueba.api.model.Dto.ClienteDto;
import com.prueba.api.model.entity.Cliente;
import com.prueba.api.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteDao clienteDao;

    @Transactional
    @Override
    public ResponseEntity<?> save(ClienteDto clienteDto) {
       Cliente cliente = Mappings.DtoAentity(clienteDto);
        try {
            clienteDao.save(cliente);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("Se ha registrado correctamente")
                    .object(Mappings.EntityAdto(cliente))
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException exDTA) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("No se ha podido registrar")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(ClienteDto clienteDto) {
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        Cliente cliente = Mappings.DtoAentity(clienteDto);
        if (clienteDao.existsById(clienteDto.getIdCliente())){
            clienteDao.save(cliente);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("Se ha actualizado correctamente")
                    .object(clienteDtoList.add(Mappings.EntityAdto(cliente)))
                    .build(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("No se ha podido registrar")
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> findById(Integer id) {
        Cliente cliente = clienteDao.findById(id).orElse(null);
        if (cliente == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("No se encontro el ID")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensanje("Se ha encontrado")
                .object(clienteDtoList.add(Mappings.EntityAdto(cliente)))
                .build(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> buscarAll() {
        List<Cliente> all = (List<Cliente>) clienteDao.findAll();

        if (all.isEmpty()){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("No se encontraron datos")
                    .object(null)
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("Datos encontrados: ")
                    .object(all)
                    .build(), HttpStatus.OK);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> delete(Integer id) {
        Cliente cliente = clienteDao.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el ID: "+id));
        clienteDao.delete(cliente);
        List<ClienteDto> clienteDtoList = new ArrayList<>();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensanje("Ha sido eliminado el ID")
                    .object(clienteDtoList.add(Mappings.EntityAdto(cliente)))
                    .build(), HttpStatus.OK);
    }
}
