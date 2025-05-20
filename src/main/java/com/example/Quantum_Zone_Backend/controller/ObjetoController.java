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

import com.example.Quantum_Zone_Backend.service.JwtService;
import com.example.Quantum_Zone_Backend.service.ObjetoService;
import com.example.Quantum_Zone_Backend.modelo.Objeto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/quantumZone/objetos")
@Tag(name = "Objeto", description = "Controlador de objetos")
public class ObjetoController {
	private final ObjetoService objetoService;
	private final JwtService jwtService;
	
	@Autowired
	public ObjetoController(ObjetoService objetoService, JwtService jwtService) {
		this.objetoService = objetoService;
		this.jwtService = jwtService;
	}
	
	@GetMapping
	@Operation(summary = "Obtener todos los objetos", description = "Devuelve una lista de todos los objetos registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de objetos obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<Objeto>> getAllObjetos(@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(objetoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obtener objeto por ID", description = "Devuelve un objeto específico basado en su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Objeto encontrado"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado")
	})
	public ResponseEntity<Objeto> getObjetoById(@PathVariable @Parameter(description = "ID del objeto") int id, @RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return objetoService.findById(id)
				.map(objeto -> new ResponseEntity<>(objeto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping
	@Operation(summary = "Crear un nuevo objeto", description = "Crea un nuevo objeto y lo guarda en la base de datos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Objeto creado con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	public ResponseEntity<Objeto> createObjeto(@RequestBody Objeto objeto) {
		Objeto nuevoObjeto = objetoService.save(objeto);
		return new ResponseEntity<>(nuevoObjeto, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar objeto", description = "Actualiza un objeto existente basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Objeto actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado") })
	public ResponseEntity<Objeto> updateObjeto(@PathVariable @Parameter(description = "ID del objeto") int id, @RequestBody Objeto objeto, 
												@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return objetoService.findById(id)
				.map(existingObjeto -> {
					objeto.setId(id);
					Objeto updatedObjeto = objetoService.save(objeto);
					return new ResponseEntity<>(updatedObjeto, HttpStatus.OK);
				})
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar objeto", description = "Elimina un objeto existente basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Objeto eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado") })
	public ResponseEntity<Void> deleteObjeto(@PathVariable @Parameter(description = "ID del objeto") int id, @RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		boolean objetoExists = objetoService.deleteById(id);
		return objetoExists ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/filtros")
	@Operation(summary = "Buscar objetos por filtros", description = "Devuelve una lista de objetos que coinciden con los filtros proporcionados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de objetos filtrados obtenida con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	public ResponseEntity<List<Objeto>> getObjetosByFilters(
			@RequestHeader(value = "Authorization", required = false) String authHeader,
			@RequestParam(required = false) @Parameter(description = "nombre") String nombre) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return objetoService.findByFilters(nombre)
				.map(objetos -> new ResponseEntity<>(objetos, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}	
}
