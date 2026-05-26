package com.agenda.demo.controllers;

import java.util.List;
import java.util.Optional;
import com.agenda.demo.dtos.TareaRequestDTO;
import com.agenda.demo.dtos.TareaResponseDTO;
import com.agenda.demo.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/all")
    public List<TareaResponseDTO> all() {
        return tareaService.obtenerTodas();
    }

    @PostMapping("/save")
    public TareaResponseDTO save(@RequestBody TareaRequestDTO tareaRequestDTO) {
        return tareaService.crearTarea(tareaRequestDTO);
    }
    @PutMapping("/update/{id}")
    public Optional<TareaResponseDTO> update(@PathVariable Long id, @RequestBody TareaRequestDTO dto) {
        return tareaService.actualizarTarea(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boolean eliminado = tareaService.eliminarTarea(id);
        return eliminado ? "Tarea eliminada con éxito" : "No se encontró la tarea";
    }
    
    @GetMapping("/cliente/{clienteId}")
    public List<TareaResponseDTO> getByCliente(@PathVariable Long clienteId) {
        return tareaService.obtenerTareasPorCliente(clienteId);
    }
}