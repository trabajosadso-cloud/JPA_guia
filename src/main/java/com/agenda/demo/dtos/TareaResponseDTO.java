package com.agenda.demo.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaResponseDTO {
    private Long id;
    private String descripcion;
    private LocalDateTime horario;
    private String estado;
    private String nombreUsuario;
    private String nombreCliente;
}