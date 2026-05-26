package com.agenda.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenda.demo.dtos.UsuarioRequestDTO;
import com.agenda.demo.dtos.UsuarioResponseDTO;
import com.agenda.demo.entity.Usuario;
import com.agenda.demo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO requestDTO) {
        // Mapeo manual: DTO -> Entidad segun el ejemplo enviado
        Usuario usuario = new Usuario();
        usuario.setNombre(requestDTO.getNombre());
        usuario.setCorreo(requestDTO.getCorreo());
        usuario.setDocumento(requestDTO.getDocumento());
        
        Usuario saved = usuarioRepository.save(usuario);

        // Mapeo manual: Entidad -> DTO Response segun el ejemplo enviado
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(saved.getId());
        response.setNombre(saved.getNombre());
        response.setCorreo(saved.getCorreo());
        response.setDocumento(saved.getDocumento());
        return response;
    }

    @Transactional
    public List<UsuarioResponseDTO> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> lista = new ArrayList<>();

        // Bucle for segun el ejemplo enviado
        for (Usuario usuario : usuarios) {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(usuario.getId());
            dto.setNombre(usuario.getNombre());
            dto.setCorreo(usuario.getCorreo());
            dto.setDocumento(usuario.getDocumento());
            lista.add(dto);
        }
        return lista;
    }
}
