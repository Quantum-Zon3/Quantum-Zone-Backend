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

@RestController
@RequestMapping("/quantumZone/videojuegos")
@Tag(name = "Videojuegos", description = "API para la gestion de videojuegos")
public class VideojuegoController {
	
	private VideoJuegoService videoJuegoService;
	
	@Autowired
	public VideojuegoController(VideoJuegoService videoJuegoService) {
		this.videoJuegoService = videoJuegoService;
	}
	
	// Obtener todos los videojuegos
	@GetMapping
	@Operation(summary = "Obtener todos los videojuegos", description = "Devuelve una lista de todos los videojuegos registrados.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de videojuegos obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
	public ResponseEntity<List<VideoJuego>> getAllVideojuegos() {
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
	public ResponseEntity<VideoJuego> getVideojuegoById(@PathVariable @Parameter(description = "ID del videojuego") String id) {
		VideoJuego videojuego = videoJuegoService.findById(id);
		if (videojuego != null) {
			return new ResponseEntity<>(videojuego, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Crear videojuego
	@PostMapping("/{id}")
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
	public ResponseEntity<VideoJuego> updateVideojuego(@PathVariable @Parameter(description = "ID del videojuego") String id, @RequestBody @Parameter(description = "Datos Actualizados del videojuego") VideoJuego videojuego) {
		VideoJuego videojuegoExistente = videoJuegoService.findById(id);
		if (videojuegoExistente != null) {
			videojuegoExistente.setId(id);
			VideoJuego videojuegoActualizado = videoJuegoService.update(videojuego);
			return new ResponseEntity<>(videojuegoActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	// Eliminar videojuego
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un videojuego", description = "Elimina un videojuego basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Videojuego eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })
	public ResponseEntity<Void> deleteVideojuego(@PathVariable @Parameter(description = "ID del videojuego")String id) {
		VideoJuego videojuegoExistente = videoJuegoService.findById(id);
		if (videojuegoExistente != null) {
			videoJuegoService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Buscar puesto por filtros
	@GetMapping("/buscar")
	@Operation(summary = "Buscar videojuegos por filtros", description = "Busca videojuegos por nombre, fecha de publicacion. descripcion, publico y/o tipo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videojuegos encontrados"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
	public ResponseEntity<List<VideoJuego>> getVideojuegos(
			
				@RequestParam(required = false) @Parameter (description = "Nombre del videojuego") String nombre,
				@RequestParam(required = false) @Parameter (description = "Fecha de publicacion del videojuego")LocalDate fechaDePublicacion,
				@RequestParam(required = false) @Parameter (description = "Descripcion del videojuego")String descripcion,
				@RequestParam(required = false) @Parameter (description = "Publico al que va dirigido el videojuego")String publico,
				@RequestParam(required = false) @Parameter (description = "Tipo de juego")String tipo){
		List<VideoJuego> videojuegosFiltrados = videoJuegoService.findByFilters(nombre, fechaDePublicacion, descripcion, publico, tipo);
		if(videojuegosFiltrados.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(videojuegosFiltrados, HttpStatus.OK);	
	}
}