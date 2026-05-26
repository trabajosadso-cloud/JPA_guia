package com.agenda.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data // Genera Getters, Setters, toString, etc.
@NoArgsConstructor // Constructor vacío para JPA
@AllArgsConstructor // Constructor con todos los campos
@Builder // Facilita la creación de objetos
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String documento;
}
