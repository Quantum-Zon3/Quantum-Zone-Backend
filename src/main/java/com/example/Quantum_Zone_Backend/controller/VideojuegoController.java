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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.Quantum_Zone_Backend.service.VideoJuegoService;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/quantumZone/videojuegos")
@Tag(name = "Videojuegos", description = "API para la gestion de videojuegos")
public class VideojuegoController {
	
	private VideoJuegoService videoJuegoService;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public VideojuegoController(VideoJuegoService videoJuegoService, JwtService jwtService, PasswordEncoder passwordEncoder) {
		this.videoJuegoService = videoJuegoService;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		
		
	}
	
	// Obtener todos los videojuegos
	@GetMapping
	@Operation(summary = "Obtener todos los videojuegos", description = "Devuelve una lista de todos los videojuegos registrados.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de videojuegos obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
	public ResponseEntity<?> getAllVideojuegos(@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		List<VideoJuego> videojuegos = videoJuegoService.findAll();
		return new ResponseEntity<>(videojuegos, HttpStatus.OK);
	}
	
	// Obtener videojuego por id
	@GetMapping("/{id}")
	@Operation(summary = "Obtener videojuegos por ID", description = "Devuelve un videojuego específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "videojuego encontrado"),
            @ApiResponse(responseCode = "404", description = "videojuego no encontrado")
    })
	public ResponseEntity<?> getVideojuegoById(@PathVariable @Parameter(description = "ID del videojuego") int id,@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return videoJuegoService.findById(id)
				.map(videojuego -> new ResponseEntity<>(videojuego, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	// Crear videojuego
	@PostMapping
	@Operation(summary = "Crear un nuevo videojuego", description = "Crea un nuevo videojuego con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "videojuego agregado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
	public ResponseEntity<VideoJuego> createVideojuego(@RequestBody@Parameter(description = "Datos del videojuego a crear") VideoJuego videojuego) {
		VideoJuego nuevoVideojuego = videoJuegoService.save(videojuego);
		return new ResponseEntity<>(nuevoVideojuego, HttpStatus.CREATED);
	}
	
	// Actualizar videojueg
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un videojuego", description = "Actualiza los datos de un videojuego existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videojuego actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })
	public ResponseEntity<VideoJuego> updateVideojuego(@PathVariable @Parameter(description = "id") int id, @RequestBody @Parameter(description = "videojuego") VideoJuego videojuego,@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return videoJuegoService.update(id, videojuego)
				.map(videojuegoExistente -> new ResponseEntity<>(videojuegoExistente, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	// Eliminar videojuego
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un videojuego", description = "Elimina un videojuego basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Videojuego eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })
	public ResponseEntity<Void> deleteVideojuego(@PathVariable @Parameter(description = "ID del videojuego") int id,@RequestHeader(value = "Authorization", required = false) String authHeader) {
		String token = this.jwtService.extractToken(authHeader);
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = videoJuegoService.deleteById(id);
		return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//Buscar puesto por filtros
	@GetMapping("/buscar")
	@Operation(summary = "Buscar videojuegos por filtros", description = "Busca videojuegos por nombre, fecha de publicacion. descripcion, publico y/o tipo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videojuegos encontrados"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
	public ResponseEntity<List<VideoJuego>> getVideojuegos(
			
				@RequestParam(required = false) @Parameter (description = "Nombre del videojuego") String nombre){
		return videoJuegoService.findByFilters(nombre)
				.map(videojuegos -> new ResponseEntity<>(videojuegos, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}