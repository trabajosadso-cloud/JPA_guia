package com.primerproyecto.primerproyecto.dto;

import jakarta.validation.constraints.*;  // Validaciones
import lombok.*;  // Lombok

@Data  // Getters/setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {  // Clase simple: no es entidad, solo datos para API

    @NotBlank(message = "El nombre no puede estar vacío")  // Validación en DTO (más flexible que en entidad)
    private String nombre;  // Solo campos de input (sin ID)

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @Positive(message = "La edad debe ser positiva")
    private Integer edad;
}