package com.primerproyecto.primerproyecto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.primerproyecto.primerproyecto.dto.UsuarioRequestDTO;
import com.primerproyecto.primerproyecto.dto.UsuarioResponseDTO;
import com.primerproyecto.primerproyecto.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primerproyecto.primerproyecto.entity.Usuario;

@Service  // Spring: singleton bean
public class UsuarioService {  // Clase de servicio: orquesta repo y mapeos

    @Autowired
    private UsuarioRepository usuarioRepository;  // Repo inyectado manualmente

    // CREATE: Crea nuevo usuario
    @Transactional  // Abre transacción: si falla, rollback (e.g., email duplicado)
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO requestDTO) {  // Input: DTO request, output: DTO response
        // Mapeo: DTO request -> Entidad (sin ID)
        Usuario usuario = new Usuario();  // Crea nueva instancia
        usuario.setNombre(requestDTO.getNombre());  // Copia campos
        usuario.setEmail(requestDTO.getEmail());
        usuario.setEdad(requestDTO.getEdad());
        Usuario saved = usuarioRepository.save(usuario);  // Guarda en BD: genera ID

        // Mapeo: Entidad -> DTO response (inline)
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(saved.getId());
        response.setNombre(saved.getNombre());
        response.setEmail(saved.getEmail());
        response.setEdad(saved.getEdad());
        return response;
    }

    // READ: Todos los usuarios
    @Transactional // Solo lectura: optimiza (no flush, locks)
    public List<UsuarioResponseDTO> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();  // Lista de entidades
        List<UsuarioResponseDTO> lista = new ArrayList<>();  // Lista vacía para DTOs

        // Bucle for para mapear cada uno
        for (Usuario usuario : usuarios) {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();  // Nuevo DTO
            dto.setId(usuario.getId());  // Copia campos
            dto.setNombre(usuario.getNombre());
            dto.setEmail(usuario.getEmail());
            dto.setEdad(usuario.getEdad());
            lista.add(dto);  // Agrega a la lista
        }
        return lista;  // Retorna lista de DTOs
    }

    // READ: Por ID
    @Transactional
    public Optional<UsuarioResponseDTO> obtenerPorId(Long id) {  // Retorna Optional: maneja no encontrado
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);  // Optional<Usuario>
        if (optionalUsuario.isPresent()) {  // Si existe
            Usuario usuario = optionalUsuario.get();  // Obtiene la entidad
            // Mapeo inline: Entidad -> DTO
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(usuario.getId());
            dto.setNombre(usuario.getNombre());
            dto.setEmail(usuario.getEmail());
            dto.setEdad(usuario.getEdad());
            return Optional.of(dto);  // Optional con DTO
        } else {
            return Optional.empty();  // No encontrado
        }
    }

    // UPDATE: Actualiza existente
    @Transactional
    public Optional<UsuarioResponseDTO> actualizarUsuario(Long id, UsuarioRequestDTO requestDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);  // Busca
        if (optionalUsuario.isPresent()) {  // Si existe, actualiza
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(requestDTO.getNombre());  // Sobrescribe campos
            usuario.setEmail(requestDTO.getEmail());
            usuario.setEdad(requestDTO.getEdad());
            Usuario updated = usuarioRepository.save(usuario);  // UPDATE SQL

            // Mapeo inline: Entidad actualizada -> DTO
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(updated.getId());
            dto.setNombre(updated.getNombre());
            dto.setEmail(updated.getEmail());
            dto.setEdad(updated.getEdad());
            return Optional.of(dto);  // Retorna Optional con resultado
        } else {
            return Optional.empty();  // No encontrado
        }
    }

    // DELETE: Borra por ID
    @Transactional
    public boolean eliminarUsuario(Long id) {  // Retorna true si borró
        if (usuarioRepository.existsById(id)) {  // Verifica existencia (evita error)
            usuarioRepository.deleteById(id);  // DELETE SQL
            return true;
        }
        return false;  // No encontrado
    }
}