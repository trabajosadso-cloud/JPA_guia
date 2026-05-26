package com.agenda.demo.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {
    private String nombre;
    private String correo;
    private String documento;
}