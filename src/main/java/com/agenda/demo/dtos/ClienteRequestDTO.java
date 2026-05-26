package com.agenda.demo.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDTO {
    private String nombre;
    private String correo;
}
