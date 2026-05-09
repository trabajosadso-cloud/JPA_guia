package com.primerproyecto.primerproyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.primerproyecto.primerproyecto.entity.Usuario;

@Repository  // Anotación: Spring lo gestiona como componente de persistencia
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {  // Extiende: <Entidad, TipoID>
    // No necesitas métodos: hereda findAll(), findById(id), save(entity), deleteById(id), existsById(id)
    // Si quieres custom (e.g., findByEmail), agrégalo: List<Usuario> findByEmail(String email);
}