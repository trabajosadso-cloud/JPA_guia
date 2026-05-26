package com.agenda.demo.repository;

import com.agenda.demo.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    
    // Spring Boot creará la consulta SQL para buscar tareas asociadas a este cliente
    List<Tarea> findByClienteId(Long clienteId);
    
}
