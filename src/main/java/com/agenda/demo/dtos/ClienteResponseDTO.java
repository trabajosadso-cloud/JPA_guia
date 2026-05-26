package com.agenda.demo.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
}
