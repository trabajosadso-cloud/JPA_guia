package com.agenda.demo.services;

import java.util.ArrayList;
import java.util.List;
import com.agenda.demo.dtos.ClienteRequestDTO;
import com.agenda.demo.dtos.ClienteResponseDTO;
import com.agenda.demo.entity.Cliente;
import com.agenda.demo.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponseDTO crearCliente(ClienteRequestDTO requestDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(requestDTO.getNombre());
        cliente.setCorreo(requestDTO.getCorreo());
        
        Cliente saved = clienteRepository.save(cliente);

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(saved.getId());
        response.setNombre(saved.getNombre());
        response.setCorreo(saved.getCorreo());
        return response;
    }

    @Transactional
    public List<ClienteResponseDTO> obtenerTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> lista = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteResponseDTO dto = new ClienteResponseDTO();
            dto.setId(cliente.getId());
            dto.setNombre(cliente.getNombre());
            dto.setCorreo(cliente.getCorreo());
            lista.add(dto);
        }
        return lista;
    }
}