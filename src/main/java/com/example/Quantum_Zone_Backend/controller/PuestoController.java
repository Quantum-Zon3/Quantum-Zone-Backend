package com.example.Quantum_Zone_Backend.controller;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.Quantum_Zone_Backend.modelo.Consola;
import com.example.Quantum_Zone_Backend.modelo.Puesto;
import com.example.Quantum_Zone_Backend.service.PuestoService;
import com.example.Quantum_Zone_Backend.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/quantumZone/puestos")
@Tag(name = "Puestos", description = "API para la gestion de puestos")
public class PuestoController {

	private final PuestoService puestoService;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public PuestoController(JwtService jwtService,PuestoService puestoService, PasswordEncoder passwordEncoder) {
		this.puestoService = puestoService;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	//Obtener todos los puestos
	@GetMapping
	@Operation(summary = "Obtener todos los puestos", description = "Devuelve una lista de todos los puestos registrados.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de puestos obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
	public ResponseEntity<?> getAllPuestos(@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		List<Puesto> puestos = puestoService.findAll();
		return new ResponseEntity<>(puestos, HttpStatus.OK);
	}
	
	//Obtener puesto por id
	@GetMapping("/{id}")
	@Operation(summary = "Obtener puestos por ID", description = "Devuelve un puesto específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "puesto encontrado"),
            @ApiResponse(responseCode = "404", description = "puesto no encontrado")
    })
	public ResponseEntity<?> getPuestoById(@PathVariable  @Parameter(description = "ID del puesto")  int id,@RequestHeader(value = "Authorization", required = false) String authHeader) {

		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return puestoService.findById(id)
				.map(puesto -> new ResponseEntity<>(puesto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Crear puesto
	@PostMapping
	@Operation(summary = "Crear puesto", description = "Crea un nuevo puesto en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Puesto creado con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
	})
	public ResponseEntity<Puesto> createPuesto(@RequestBody @Parameter(description = "Datos del puesto a crear") Puesto puesto) {
		Puesto nuevoPuesto = puestoService.save(puesto);
		return new ResponseEntity<>(nuevoPuesto, HttpStatus.CREATED);
	}
	//Actualizar puesto
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar puesto", description = "Actualiza un puesto existente en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Puesto actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "Puesto no encontrado")
	})	
	public ResponseEntity<Puesto> updatePuesto(@PathVariable @Parameter(description = "ID del puesto") int id, @RequestBody @Parameter(description = "Datos actualizados del puesto") Puesto puesto,@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return puestoService.update(id, puesto)
				.map(puestoExistente -> new ResponseEntity<>(puestoExistente, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//Eliminar puesto
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar puesto", description = "Elimina un puesto existente del sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Puesto eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "Puesto no encontrado")
	})
	public ResponseEntity<Void> deletePuesto(@PathVariable @Parameter(description = "ID del puestoo") int id,@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = puestoService.deleteById(id);
		return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//Buscar puesto por filtros
	@GetMapping("/buscar")
	@Operation(summary = "Buscar puestos por filtros", description = "Devuelve una lista de puestos que coinciden con los filtros proporcionados.")
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de puestos filtrados obtenida con éxito"),
			@ApiResponse(responseCode = "204", description = "No se encontraron puestos que coincidan con los filtros")
	})
	public ResponseEntity<List<Puesto>> getPuestos(
			
			@RequestParam(required = false)  @Parameter (description = "Numero de puestos en puesto") String numeroDePuesto) {		
		return puestoService.findByFilters(numeroDePuesto)
				.map(puestos -> new ResponseEntity<>(puestos, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
}