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
import com.example.Quantum_Zone_Backend.service.JwtService;
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

	private final JwtService jwtService;
	private final AdministradorService administradorService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AdministradorController(JwtService jetService, AdministradorService administradorService, PasswordEncoder passwordEncoder) {
		this.jwtService = jetService;
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
				String jwt = this.jwtService.generateToken(administrador);
				return ResponseEntity.ok(new LoginResponse(jwt, administrador.getCedula(), administrador.getNombre()));
			} else {
				return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>("Administrador no encontrado", HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping
	@Operation(summary = "Obtener todos los administradores", description = "Devuelve una lista de todos los administradores registrados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de administradores obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<?> getAllAdministradores(@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null || !this.jwtService.validateJwtToken(token)) {
			return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
		}
		List<Administrador> administradores = administradorService.findAll();
		return new ResponseEntity<>(administradores, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener administrador por ID", description = "Devuelve un administrador específico basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
			@ApiResponse(responseCode = "404", description = "Administrador no encontrado") })
	public ResponseEntity<?> getAdministradorById(
			@PathVariable @Parameter(description = "ID del administrador") Integer id,@RequestHeader(value = "Authorization", required = false) String authHeader ) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null || !this.jwtService.validateJwtToken(token)) {
			return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
		}
		return administradorService.findById(id)
				.map(administrador -> new ResponseEntity<>(administrador, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
	public ResponseEntity<?> updateAdministrador(
			@PathVariable @Parameter(description = "ID del administrador") Integer id,
			@RequestBody @Parameter(description = "Datos del administrador a actualizar") Administrador administrador,
			@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null || !this.jwtService.validateJwtToken(token)) {
			return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
		}
		return administradorService.update(id, administrador)
				.map(updatedAdministrador -> new ResponseEntity<>(updatedAdministrador, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un administrador", description = "Elimina un administrador existente basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrador eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "Administrador no encontrado") })
	public ResponseEntity<Void> deleteAdministrador(
			@PathVariable @Parameter(description = "ID del administrador") int id,
			@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null || !this.jwtService.validateJwtToken(token)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = administradorService.deleteById(id);
		return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/filtros")
	@Operation(summary = "Buscar administradores por filtros", description = "Devuelve una lista de administradores que coinciden con los filtros proporcionados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de administradores filtrada obtenida con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	public ResponseEntity<List<Administrador>> getAdministradoresByFilters(
			@RequestParam(required = false) @Parameter(description = "Nombre del administrador") String nombre) {
		return administradorService.findByFilters(nombre)
				.map(administradores -> new ResponseEntity<>(administradores, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	// Inner class for login request
    public static class LoginRequest {
        private String cedula;
        private String contraseña;
        
        public LoginRequest(String cedula, String contraseña) {
			this.cedula = cedula;
			this.contraseña = contraseña;
		}

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
    
    public static class LoginResponse {
        private String token;
        private String cedula;
        private String nombre;
        public LoginResponse(String token, String cedula, String nombre) {
            this.token = token;
            this.cedula = cedula;
            this.nombre = nombre;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }


}
