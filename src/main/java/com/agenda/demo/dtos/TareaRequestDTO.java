package com.agenda.demo.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaRequestDTO {
    private String descripcion;
    private LocalDateTime horario;
    private String estado;
    private Long usuarioId; // Solo pedimos el ID para buscarlo luego
    private Long clienteId; // Solo pedimos el ID
}
