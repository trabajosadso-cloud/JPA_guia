package com.primerproyecto.primerproyecto.controllers;

import java.util.List;

import com.primerproyecto.primerproyecto.dto.UsuarioRequestDTO;
import com.primerproyecto.primerproyecto.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.primerproyecto.primerproyecto.services.UsuarioService;

@RestController  // @Controller + @ResponseBody: retorna JSON auto
@RequestMapping("/api/usuarios")  // Base URL: todos endpoints empiezan aquí (RESTful)
public class UsuarioController {  // Clase para manejar /api/usuarios/*

    @Autowired
    private UsuarioService usuarioService;  // Inyecta service manualmente

    // CREATE: POST /api/usuarios
    @PostMapping  // Mapea POST a este método
    public ResponseEntity<UsuarioResponseDTO> crear(@RequestBody UsuarioRequestDTO requestDTO) {  // @RequestBody: JSON -> objeto
        UsuarioResponseDTO response = usuarioService.crearUsuario(requestDTO);  // Llama service
        return ResponseEntity.status(HttpStatus.CREATED).body(response);  // 201 Created + body JSON
    }

    // READ: GET /api/usuarios (todos)
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {
        List<UsuarioResponseDTO> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);  // 200 OK + lista JSON
    }

    // READ: GET /api/usuarios/{id}
    @GetMapping("/{id}")  // {id} es path variable
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {  // @PathVariable: extrae id de URL
        UsuarioResponseDTO usuario = usuarioService.obtenerPorId(id).orElse(null);  // Optional
        if (usuario != null) {
            return ResponseEntity.ok(usuario);  // Si existe: 200 + body
        } else {
            return ResponseEntity.notFound().build();  // Sino: 404 No Content
        }
    }

    // UPDATE: PUT /api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO requestDTO) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.actualizarUsuario(id, requestDTO).orElse(null);  // 200 OK si éxito
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();  // 404 si no
        }
    }

    // DELETE: DELETE /api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {  // Void: no body
        if (usuarioService.eliminarUsuario(id)) {
            return ResponseEntity.noContent().build();  // 204 No Content (éxito sin body)
        }
        return ResponseEntity.notFound().build();  // 404
    }
}