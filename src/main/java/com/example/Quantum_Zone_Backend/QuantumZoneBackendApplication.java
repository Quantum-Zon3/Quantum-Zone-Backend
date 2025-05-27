package com.example.Quantum_Zone_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class QuantumZoneBackendApplication {

	public static void main(String[] args) {
		// Configura Dotenv para que ignore si el archivo .env no se encuentra
		Dotenv dotenv = Dotenv.configure()
						.ignoreIfMissing()
						.load();

				// Establece las propiedades solo si no existen como variables de entorno
				dotenv.entries().forEach(entry -> {
					if (System.getProperty(entry.getKey()) == null &&
							System.getenv(entry.getKey()) == null) {
						System.setProperty(entry.getKey(), entry.getValue());
					}
		});
		SpringApplication.run(QuantumZoneBackendApplication.class, args);
	}

}
