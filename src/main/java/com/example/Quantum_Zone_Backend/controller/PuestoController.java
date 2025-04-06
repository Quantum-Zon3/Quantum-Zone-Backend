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

@RestController
@RequestMapping("/quantumZone/puestos")
@Tag(name = "Puestos", description = "API para la gestion de puestos")
public class PuestoController {

	private final PuestoService puestoService;
	
	@Autowired
	public PuestoController(PuestoService puestoService) {
		this.puestoService = puestoService;
	}
	
	//Obtener todos los puestos
	@GetMapping
	@Operation(summary = "Obtener todos los puestos", description = "Devuelve una lista de todos los puestos registrados.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de puestos obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
	public ResponseEntity<List<Puesto>> getAllPuestos() {
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
	public ResponseEntity<Puesto> getPuestoById(@PathVariable  @Parameter(description = "ID del puesto")  String id) {
		Puesto puesto = puestoService.findById(id);
		if (puesto != null) {
			return new ResponseEntity<>(puesto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
	@Operation(summary = "Actualizar puesto", description = "Actualiza un puesto existente en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Puesto actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "Puesto no encontrado")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Puesto> updatePuesto(@PathVariable @Parameter(description = "ID del puesto") String id, @RequestBody @Parameter(description = "Datos actualizados del puesto") Puesto puesto) {
		Puesto puestoExistente = puestoService.findById(id);		
		if (puestoExistente != null) {
			
			Puesto puestoActualizado = puestoService.update(puesto);
			return new ResponseEntity<>(puestoActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Eliminar puesto
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar puesto", description = "Elimina un puesto existente del sistema.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Puesto eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "Puesto no encontrado")
	})
	public ResponseEntity<Void> deletePuesto(@PathVariable @Parameter(description = "ID del puestoo") String id) {
		Puesto puestoExistente = puestoService.findById(id);
		if (puestoExistente != null) {
			puestoService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Buscar puesto por filtros
	@GetMapping("/buscar")
	@Operation(summary = "Buscar puestos por filtros", description = "Devuelve una lista de puestos que coinciden con los filtros proporcionados.")
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de puestos filtrados obtenida con éxito"),
			@ApiResponse(responseCode = "204", description = "No se encontraron puestos que coincidan con los filtros")
	})
	public ResponseEntity<List<Puesto>> getPuestos(
			
			@RequestParam(required = false)  @Parameter (description = "Numero de puestos en puesto") String numeroDePuesto,
			@RequestParam(required = false)  @Parameter (description = "Consola en la que esta el puesto")Consola consola,
			@RequestParam(required = false)  @Parameter (description = "Cantidad de filas en puesto")int cantidadDeSillas,
			@RequestParam(required = false)  @Parameter (description = "Cantidad de controles para la consola del puesto")int canditadDeControles) {		
		List<Puesto> puestosFiltrados = puestoService.findByFilters(numeroDePuesto, consola, cantidadDeSillas, canditadDeControles);
		if (puestosFiltrados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(puestosFiltrados, HttpStatus.OK);
	}
}