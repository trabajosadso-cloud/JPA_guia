package com.primerproyecto.primerproyecto.entity;

import jakarta.persistence.*;  // Importa anotaciones JPA (jakarta desde Spring 3.x, antes javax)
import lombok.*;  // Lombok: genera código auto

@Entity  // Anotación JPA: esta clase es una entidad (tabla en BD)
@Table(name = "usuarios")  // Nombre de la tabla (plural para convención; JPA usa snake_case)
@Data  // Lombok: genera getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Lombok: constructor vacío (requerido por JPA para instanciar)
@AllArgsConstructor  // Lombok: constructor con todos los args (útil para tests)
@Builder  // Lombok: patrón Builder para crear objetos inmutables (e.g., Usuario.builder().nombre("Juan").build())
public class Usuario {  // Clase POJO (Plain Old Java Object): representa un usuario

    @Id  // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremental (MySQL usa AUTO_INCREMENT)
    private Long id;  // ID numérico (Long para grandes volúmenes)

    @Column(nullable = false)  // Columna SQL: NOT NULL
    private String nombre;  // Campo simple: String para texto

    @Column(nullable = false, unique = true)  // NOT NULL + UNIQUE (evita emails duplicados)
    private String email;  // Email como String

    @Column(nullable = false)  // NOT NULL
    private Integer edad;  // Entero para edad (Integer permite null, pero validamos)
}
