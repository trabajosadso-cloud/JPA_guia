package com.agenda.demo.controllers;

import java.util.List;
import com.agenda.demo.dtos.UsuarioRequestDTO;
import com.agenda.demo.dtos.UsuarioResponseDTO;
import com.agenda.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public List<UsuarioResponseDTO> all() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping("/save")
    public UsuarioResponseDTO save(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.crearUsuario(usuarioRequestDTO);
    }
}

//Prueba de archivos en git