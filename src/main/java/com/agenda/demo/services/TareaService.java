package com.agenda.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenda.demo.dtos.TareaRequestDTO;
import com.agenda.demo.dtos.TareaResponseDTO;
import com.agenda.demo.entity.Tarea;
import com.agenda.demo.entity.Usuario;
import com.agenda.demo.entity.Cliente;
import com.agenda.demo.repository.TareaRepository;
import com.agenda.demo.repository.UsuarioRepository;
import com.agenda.demo.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    // Necesitamos los TRES repositorios inyectados para que funcione
    @Autowired
    private TareaRepository tareaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public TareaResponseDTO crearTarea(TareaRequestDTO requestDTO) {
        // 1. Buscamos si el Usuario y el Cliente existen en la BD
        Optional<Usuario> userOpt = usuarioRepository.findById(requestDTO.getUsuarioId());
        Optional<Cliente> clientOpt = clienteRepository.findById(requestDTO.getClienteId());

        // 2. Si ambos existen, procedemos a crear la tarea
        if (userOpt.isPresent() && clientOpt.isPresent()) {
            Tarea tarea = new Tarea();
            tarea.setDescripcion(requestDTO.getDescripcion());
            tarea.setHorario(requestDTO.getHorario());
            tarea.setEstado(requestDTO.getEstado());
            
            // Asignamos los objetos completos, no solo los IDs
            tarea.setUsuario(userOpt.get());
            tarea.setCliente(clientOpt.get());

            Tarea saved = tareaRepository.save(tarea);

            // 3. Mapeamos el resultado al DTO de respuesta
            TareaResponseDTO response = new TareaResponseDTO();
            response.setId(saved.getId());
            response.setDescripcion(saved.getDescripcion());
            response.setHorario(saved.getHorario());
            response.setEstado(saved.getEstado());
            response.setNombreUsuario(saved.getUsuario().getNombre());
            response.setNombreCliente(saved.getCliente().getNombre());
            
            return response;
        }
        
        throw new RuntimeException("No se pudo crear la tarea: Usuario o Cliente no encontrados.");
    }

    @Transactional
    public List<TareaResponseDTO> obtenerTodas() {
        List<Tarea> tareas = tareaRepository.findAll();
        List<TareaResponseDTO> lista = new ArrayList<>();

        for (Tarea tarea : tareas) {
            TareaResponseDTO dto = new TareaResponseDTO();
            dto.setId(tarea.getId());
            dto.setDescripcion(tarea.getDescripcion());
            dto.setHorario(tarea.getHorario());
            dto.setEstado(tarea.getEstado());
            dto.setNombreUsuario(tarea.getUsuario().getNombre());
            dto.setNombreCliente(tarea.getCliente().getNombre());
            lista.add(dto);
        }
        return lista;
    }
    // UPDATE: Actualiza el estado o descripción
    @Transactional
    public Optional<TareaResponseDTO> actualizarTarea(Long id, TareaRequestDTO requestDTO) {
        Optional<Tarea> optionalTarea = tareaRepository.findById(id);
        if (optionalTarea.isPresent()) {
            Tarea tarea = optionalTarea.get();
            tarea.setDescripcion(requestDTO.getDescripcion());
            tarea.setEstado(requestDTO.getEstado());
            tarea.setHorario(requestDTO.getHorario());
            
            Tarea updated = tareaRepository.save(tarea);

            TareaResponseDTO dto = new TareaResponseDTO();
            dto.setId(updated.getId());
            dto.setDescripcion(updated.getDescripcion());
            dto.setEstado(updated.getEstado());
            dto.setHorario(updated.getHorario());
            dto.setNombreUsuario(updated.getUsuario().getNombre());
            dto.setNombreCliente(updated.getCliente().getNombre());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    // DELETE: Borra la tarea
    @Transactional
    public boolean eliminarTarea(Long id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional
    public List<TareaResponseDTO> obtenerTareasPorCliente(Long clienteId) {
        // Buscamos usando el método que acabamos de definir en el repositorio
        List<Tarea> tareas = tareaRepository.findByClienteId(clienteId);
        List<TareaResponseDTO> lista = new ArrayList<>();

        for (Tarea tarea : tareas) {
            TareaResponseDTO dto = new TareaResponseDTO();
            dto.setId(tarea.getId());
            dto.setDescripcion(tarea.getDescripcion());
            dto.setHorario(tarea.getHorario());
            dto.setEstado(tarea.getEstado());
            dto.setNombreUsuario(tarea.getUsuario().getNombre());
            dto.setNombreCliente(tarea.getCliente().getNombre());
            lista.add(dto);
        }
        return lista;
    }
}