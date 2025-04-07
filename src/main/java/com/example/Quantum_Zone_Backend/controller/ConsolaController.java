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

@RestController
@RequestMapping("/quantumZone/consolas")
@Tag(name = "Consola", description = "Controlador de consolas")
public class ConsolaController {
	private final ConsolaService consolaService;
	
	@Autowired
	public ConsolaController(ConsolaService consolaService) {
		this.consolaService = consolaService;
	}
	
	@GetMapping
	@Operation(summary = "Obtener todas las consolas", description = "Devuelve una lista de todas las consolas registradas.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de consolas obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<Consola>> getAllConsolas() {
		List<Consola> consolas = consolaService.findAll();
		return new ResponseEntity<>(consolas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obtener consola por ID", description = "Devuelve una consola específica basada en su ID.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consola encontrada"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada")
	})
	public ResponseEntity<Consola> getConsolaById(@PathVariable @Parameter(description = "ID de la consola") String id) {
		Consola consola = consolaService.findById(id);
		if (consola != null) {
			return new ResponseEntity<>(consola, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@Operation(summary = "Crear nueva consola", description = "Crea una nueva consola y la guarda en la base de datos.")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Consola creada con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Consola> createConsola(@RequestBody Consola consola) {
		Consola nuevaConsola = consolaService.save(consola);
		return new ResponseEntity<>(nuevaConsola, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar consola", description = "Actualiza una consola existente basada en su ID.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consola actualizada con éxito"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Consola> updateConsola(@PathVariable @Parameter(description = "ID de la consola") String id,
			@RequestBody Consola consola) {
		Consola consolaExistente = consolaService.findById(id);
		if (consolaExistente != null) {
			consola.setId(id);
			Consola consolaActualizada = consolaService.update(consola);
			return new ResponseEntity<>(consolaActualizada, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar consola", description = "Elimina una consola existente basada en su ID.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consola eliminada con éxito"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<Void> deleteConsola(@PathVariable @Parameter(description = "ID de la consola") String id) {
		Consola consolaExistente = consolaService.findById(id);
		if (consolaExistente != null) {
			consolaService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/buscar")
	@Operation(summary = "Buscar consolas por filtros", description = "Devuelve una lista de consolas que coinciden con los filtros proporcionados.")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Consolas encontradas"),
			@ApiResponse(responseCode = "404", description = "Consola no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<List<Consola>> getConsolasByFilters(
			@RequestParam(required = false) @Parameter(description = "Marca de la consola") String marca,
			@RequestParam(required = false) @Parameter(description = "Nombre de la consola") String consola,
			@RequestParam(required = false) @Parameter(description = "Fecha de publicación de la consola") LocalDate fechaDePublicacion) {
		List<Consola> consolas = consolaService.findByFilters(marca, consola, fechaDePublicacion);
		if (consolas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(consolas, HttpStatus.OK);
	}

}
