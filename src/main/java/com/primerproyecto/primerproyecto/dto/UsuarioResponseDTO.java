package com.primerproyecto.primerproyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //Constructor sin parámetros Útil para JPA o cuando necesitas crear el objeto vacío.
@AllArgsConstructor // Constructor con todos los atributos Para crear el objeto con todos los valores de una.
@Builder // Patrón Builder Permite crear el objeto de forma clara y legible.
public class UsuarioResponseDTO {  // Incluye ID para respuestas completas

    private Long id;  // ID generado en BD
    private String nombre;
    private String email;
    private Integer edad;
}