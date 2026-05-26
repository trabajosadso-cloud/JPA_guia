package com.agenda.demo.controllers;

import java.util.List;
import com.agenda.demo.dtos.ClienteRequestDTO;
import com.agenda.demo.dtos.ClienteResponseDTO;
import com.agenda.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public List<ClienteResponseDTO> all() {
        return clienteService.obtenerTodos();
    }

    @PostMapping("/save")
    public ClienteResponseDTO save(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.crearCliente(clienteRequestDTO);
    }
}