package com.prueba.api.model.mappings;

import com.prueba.api.model.Dto.ClienteDto;
import com.prueba.api.model.entity.Cliente;

public class Mappings {

    public static Cliente DtoAentity(ClienteDto clienteDto){
        return Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .build();
    }

    public static ClienteDto EntityAdto(Cliente cliente){
        return ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .correo(cliente.getCorreo())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

}
