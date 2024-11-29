package com.example.proyecto_sgp;

import com.example.proyecto_sgp.auth.MenuAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoSgpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSgpApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MenuAuth menuAuth) {
		return args -> {
			System.out.println("=== Sistema de Gestión de Préstamos y Elementos DTI ===");
			System.out.println("Bienvenido al sistema");

			try {
				menuAuth.iniciarSesion();
			} catch (Exception e) {
				System.err.println("Error en la aplicación: " + e.getMessage());
				e.printStackTrace();
			} finally {
				System.out.println("\nGracias por usar el sistema");
			}
		};
	}
}
