package com.example.Quantum_Zone_Backend.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.Quantum_Zone_Backend.service.ConsolaService;
import com.example.Quantum_Zone_Backend.modelo.Consola;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.Quantum_Zone_Backend.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/quantumZone/consolas")
@Tag(name = "Consola", description = "Controlador de consolas")
public class ConsolaController {
	private final ConsolaService consolaService;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ConsolaController(JwtService jwtService,ConsolaService consolaService, PasswordEncoder passwordEncoder) {
		this.consolaService = consolaService;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	@GetMapping
	@Operation(summary = "Obtener todas las consolas", description = "Devuelve una lista de todas las consolas registradas.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de consolas obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<?> getAllConsolas(@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		List<Consola> consolas = consolaService.findAll();
		return new ResponseEntity<>(consolas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obtener consola por ID", description = "Devuelve una consola específica basada en su ID.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consola encontrada"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada")
	})
	public ResponseEntity<?> getConsolaById(@PathVariable @Parameter(description = "ID de la consola") int id, @RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return consolaService.findById(id)
				.map(consola -> new ResponseEntity<>(consola, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	
	@PostMapping
	@Operation(summary = "Crear nueva consola", description = "Crea una nueva consola y la guarda en la base de datos.")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Consola creada con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Consola> createConsola(@RequestBody @Parameter(description = "Datos de la nueva consola") Consola consola) {
		Consola nuevaConsola = consolaService.save(consola);
		return new ResponseEntity<>(nuevaConsola, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar consola", description = "Actualiza una consola existente basada en su ID.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consola actualizada con éxito"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Consola> updateConsola(@PathVariable @Parameter(description = "ID de la consola") int id,
			@RequestBody Consola consola, @RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return consolaService.update(id, consola)
				.map(consolaExistente -> new ResponseEntity<>(consolaExistente, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar consola", description = "Elimina una consola existente basada en su ID.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consola eliminada con éxito"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Void> deleteConsola(@PathVariable @Parameter(description = "ID de la consola") int id, @RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = consolaService.deleteById(id);
		return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/buscar")
	@Operation(summary = "Buscar consolas por filtros", description = "Devuelve una lista de consolas que coinciden con los filtros proporcionados.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consolas encontradas"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<List<Consola>> getConsolasByFilters(
			@RequestParam(required = false) @Parameter(description = "Nombre de la consola") String consola) {
		return consolaService.findByFilters(consola)
				.map(consolas -> new ResponseEntity<>(consolas, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
