package com.primerproyecto.primerproyecto;

import org.springframework.boot.SpringApplication;  // Clase para correr la app
import org.springframework.boot.autoconfigure.SpringBootApplication;  // Anotación mágica: habilita auto-configuración

@SpringBootApplication  // Anotación principal:
// - @EnableAutoConfiguration: configura beans auto (e.g., DataSource de yml)
// - @ComponentScan: escanea @Controller, @Service, etc. en este paquete y subpaquetes
// - @Configuration: permite definir beans si necesitas
public class PrimerProyectoApplication {  // Clase pública: nombre debe coincidir con application.yml implícito

    // Método main: el "main" de Java, pero con Spring
    public static void main(String[] args) {  // args: argumentos de línea de comandos (e.g., --debug)
        SpringApplication.run(PrimerProyectoApplication.class, args);  // Corre la app: crea contexto, beans, servidor Tomcat en puerto 8080
        // Al correr, crea tablas via JPA, inicia endpoints en /api/usuarios
    }
}