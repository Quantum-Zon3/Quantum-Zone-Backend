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

import com.example.Quantum_Zone_Backend.modelo.Cliente;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.modelo.VideojuegoRentado;
import com.example.Quantum_Zone_Backend.service.VideojuegoRentadoService;

@RestController
@RequestMapping("/quantumZone/videojuegos/rentados")
@Tag(name = "Videojuegos Rentados", description = "API para la gestion de videojuegos rentados")
public class VideojuegoRentadoController {
	private VideojuegoRentadoService videojuegoRentadoService;
	
	@Autowired
	public VideojuegoRentadoController(VideojuegoRentadoService videojuegoRentadoService) {
		this.videojuegoRentadoService = videojuegoRentadoService;
	}
	
	// Obtener todos los videojuegos rentados
	@GetMapping
	@Operation(summary = "Obtener todos los videojuegos rentados", description = "Devuelve una lista de todos los videojuegos rentados registrados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de videojuegos rentados obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<List<VideojuegoRentado>> getAllVideojuegosRentados() {
		List<VideojuegoRentado> videojuegosRentados = videojuegoRentadoService.findAll();
		return new ResponseEntity<>(videojuegosRentados, HttpStatus.OK);
	}
	
	// Obtener videojuego rentado por id
	@GetMapping("/{id}")
	@Operation(summary = "Obtener videojuego rentado por ID", description = "Devuelve un videojuego rentado específico basado en su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "videojuego rentado encontrado"),
			@ApiResponse(responseCode = "404", description = "videojuego rentado no encontrado")
	})
	public ResponseEntity<VideojuegoRentado> getVideojuegoRentadoById(@PathVariable @Parameter(description = "ID del videojuego rentado") int id) {
		VideojuegoRentado videojuegoRentado = videojuegoRentadoService.findById(id);
		if (videojuegoRentado != null) {
			return new ResponseEntity<>(videojuegoRentado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	// Crear un nuevo videojuego rentado
	@PostMapping
	@Operation(summary = "Crear un nuevo videojuego rentado", description = "Crea un nuevo videojuego rentado y lo guarda en la base de datos.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "videojuego rentado creado con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
	})
	public ResponseEntity<VideojuegoRentado> createVideojuegoRentado(@RequestBody VideojuegoRentado videojuegoRentado) {
		VideojuegoRentado nuevoVideojuegoRentado = videojuegoRentadoService.save(videojuegoRentado);
		return new ResponseEntity<>(nuevoVideojuegoRentado, HttpStatus.CREATED);
	}
	// Actualizar videojuego rentado
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar videojuego rentado", description = "Actualiza un videojuego rentado existente basado en su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "videojuego rentado actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "videojuego rentado no encontrado")
	})
	public ResponseEntity<VideojuegoRentado> updateVideojuegoRentado(@PathVariable int id, @RequestBody VideojuegoRentado videojuegoRentado) {
		VideojuegoRentado videojuego = videojuegoRentadoService.findById(id);
		if (videojuego != null) {
			videojuego.setId(id);
			VideojuegoRentado videojuegoActualizado = videojuegoRentadoService.update(videojuego);
			return new ResponseEntity<>(videojuegoActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Eliminar videojuego rentado
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar videojuego rentado", description = "Elimina un videojuego rentado existente basado en su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "videojuego rentado eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "videojuego rentado no encontrado")
	})
	public ResponseEntity<Void> deleteVideojuegoRentado(@PathVariable int id) {
		VideojuegoRentado videojuegoRentado = videojuegoRentadoService.findById(id);
		if (videojuegoRentado != null) {
			videojuegoRentadoService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Filtrar videojuegos rentados
	@GetMapping("/filtros")
	@Operation(summary = "Filtrar videojuegos rentados", description = "Filtra los videojuegos rentados según los parámetros proporcionados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de videojuegos rentados filtrados"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
	})
	public ResponseEntity<List<VideojuegoRentado>> filterVideojuegosRentados(
			@RequestParam(defaultValue = "0")@Parameter(required = false) int id,
			@RequestParam(required = false) String cedula,
			@RequestParam(required = false) VideoJuego videojuego,
			@RequestParam(required = false) LocalDate fechaInicio,
			@RequestParam(required = false) LocalDate fechaFin) {
		List<VideojuegoRentado> videojuegosRentados = videojuegoRentadoService.findByFilters(id, cedula, videojuego, fechaInicio, fechaFin);
		return new ResponseEntity<>(videojuegosRentados, HttpStatus.OK);
	}
	
	

}
