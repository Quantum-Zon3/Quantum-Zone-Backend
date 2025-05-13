package com.example.Quantum_Zone_Backend.controller;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Quantum_Zone_Backend.service.AdministradorService;
import com.example.Quantum_Zone_Backend.modelo.Administrador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/quantumZone/administradores")
@Tag(name = "Administrador", description = "Controlador de administradores")
public class AdministradorController {

	private final AdministradorService administradorService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AdministradorController(AdministradorService administradorService, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.administradorService = administradorService;
	}

	// Login
	@PostMapping("/login")
	@Operation(summary = "Iniciar sesión", description = "Inicia sesión con las credenciales del administrador.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
			@ApiResponse(responseCode = "401", description = "Credenciales inválidas") })
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Optional<Administrador> administradorOptional = administradorService.findById(administradorService.findAll()
				.stream().filter(administrador -> administrador.getCedula().equals(loginRequest.getCedula()))
				.findFirst().map(Administrador::getId).orElse(null));
		if (administradorOptional.isPresent()) {
			Administrador administrador = administradorOptional.get();
			if (passwordEncoder.matches(loginRequest.getContraseña(), administrador.getContraseña())) {
				return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>("Administrador no encontrado", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}

	@GetMapping
	@Operation(summary = "Obtener todos los administradores", description = "Devuelve una lista de todos los administradores registrados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de administradores obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<Administrador>> getAllAdministradores() {
		List<Administrador> administradores = administradorService.findAll();
		return new ResponseEntity<>(administradores, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener administrador por ID", description = "Devuelve un administrador específico basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
			@ApiResponse(responseCode = "404", description = "Administrador no encontrado") })
	public ResponseEntity<Administrador> getAdministradorById(
			@PathVariable @Parameter(description = "ID del administrador") int id) {
		Administrador administrador = administradorService.findById(id);
		if (administrador != null) {
			return new ResponseEntity<>(administrador, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@Operation(summary = "Crear un nuevo administrador", description = "Crea un nuevo administrador y lo guarda en la base de datos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Administrador creado con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	public ResponseEntity<Administrador> createAdministrador(
			@RequestBody @Parameter(description = "Datos del nuevo administrador") Administrador administrador) {
		Administrador nuevoAdministrador = administradorService.save(administrador);
		return new ResponseEntity<>(nuevoAdministrador, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un administrador", description = "Actualiza los datos de un administrador existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrador actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "Administrador no encontrado") })
	public ResponseEntity<Administrador> updateAdministrador(
			@PathVariable @Parameter(description = "ID del administrador") int id,
			@RequestBody @Parameter(description = "Datos del administrador a actualizar") Administrador administrador) {
		Administrador administradorActualizado = administradorService.update(administrador);
		if (administradorActualizado != null) {
			administrador.setId(id);
			return new ResponseEntity<>(administradorActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un administrador", description = "Elimina un administrador existente basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrador eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "Administrador no encontrado") })
	public ResponseEntity<Void> deleteAdministrador(
			@PathVariable @Parameter(description = "ID del administrador") int id) {
		administradorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/filtros")
	@Operation(summary = "Buscar administradores por filtros", description = "Devuelve una lista de administradores que coinciden con los filtros proporcionados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de administradores filtrada obtenida con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	public ResponseEntity<List<Administrador>> getAdministradoresByFilters(
			@RequestParam(required = false) @Parameter(description = "Nombre del administrador") String nombre,
			@RequestParam(required = false) @Parameter(description = "Cédula del administrador") String cedula,
			@RequestParam(defaultValue = "0") @Parameter(description = "Edad del administrador") int edad) {
		List<Administrador> administradoresFiltrados = administradorService.findByFilters(nombre, cedula, edad);

		// Verificar si la lista está vacía
		if (administradoresFiltrados.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(administradoresFiltrados, HttpStatus.OK);
	}
	// Inner class for login request
    public static class LoginRequest {
        private String cedula;
        private String contraseña;

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }
    }


}
