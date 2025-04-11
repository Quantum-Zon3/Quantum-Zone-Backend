package com.example.Quantum_Zone_Backend.controller;
import java.util.List;
import java.util.Map;

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

import com.example.Quantum_Zone_Backend.modelo.Consola;
import com.example.Quantum_Zone_Backend.modelo.Inventario;
import com.example.Quantum_Zone_Backend.modelo.Objeto;
import com.example.Quantum_Zone_Backend.modelo.Puesto;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/quantumZone/inventario")
@Tag(name = "Inventario", description = "Controlador de inventario")
public class InventarioController {
	private final InventarioService inventarioService;

	@Autowired
	public InventarioController(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
	}

	@GetMapping
	@Operation(summary = "Obtener todos los invetarios", description = "Devuelve una lista de todos los inventarios registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de inventario obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<Inventario>> getAllinventario() {
		List<Inventario> objetos = inventarioService.findAll();
		return new ResponseEntity<>(objetos, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@Operation(summary = "Obtener inventario por ID", description = "Devuelve un inventario específico basado en su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Objeto encontrado"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado")
	})
	public ResponseEntity<Inventario> getInvetarioById(@PathVariable @Parameter(description = "ID del inventario") String id) {
		Inventario inventario = inventarioService.findById(id);
		if (inventario != null) {
			return new ResponseEntity<>(inventario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping
	@Operation(summary = "Crear un nuevo inventario", description = "Crea un nuevo inventario y lo guarda en la base de datos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inventario creado con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
		Inventario createdInventario = inventarioService.save(inventario);
		return new ResponseEntity<>(createdInventario, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar inventario", description = "Actualiza un inventario existente en la base de datos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "inventario actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "inventario no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<Inventario> updateInventario(@PathVariable @Parameter(description = "ID del inventario") String id,
			@RequestBody Inventario invetario) {
		Inventario updateInventario = inventarioService.update(invetario);
		if (updateInventario != null) {
			updateInventario.setId(id);
			return new ResponseEntity<>(updateInventario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar inventario", description = "Elimina un inventario existente de la base de datos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "inventario eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "inventario no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<Void> deleteInventario(@PathVariable @Parameter(description = "ID del inventario") String id) {
		Inventario objeto = inventarioService.findById(id);
		if (objeto != null) {
			inventarioService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@GetMapping("/filtros")
	@Operation(summary = "Buscar inventarios por filtros", description = "Devuelve una lista de inventarios que coinciden con los filtros proporcionados.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Lista de inventarios filtrada obtenida con éxito"),
	        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
	})
	public ResponseEntity<List<Inventario>> getInventariosByFilters(
			 @RequestParam(required = false) @Parameter(description = "IDs de consolas") List<Consola> consolas,
		        @RequestParam(required = false) @Parameter(description = "IDs de videojuegos") List<VideoJuego> videojuegos,
		        @RequestParam(required = false) @Parameter(description = "IDs de objetos") List<Objeto> objetos,
		        @RequestParam(required = false) @Parameter(description = "IDs de puestos") List<Puesto> puestos)
	{
	    List<Inventario> inventariosFiltrados = inventarioService.findByFilters(consolas, videojuegos, objetos, puestos);

	    // Verificar si la lista está vacía
	    if (inventariosFiltrados.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(inventariosFiltrados, HttpStatus.OK);
	}
	@GetMapping("/consolas")
	@Operation(summary = "Obtener consolas", description = "Devuelve una lista de todas las consolas registradas en el inventario.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de consolas obtenida con éxito"),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<List<Consola>> getConsolas() {
	    List<Consola> consolas = inventarioService.getConsolas();
	    return new ResponseEntity<>(consolas, HttpStatus.OK);
	}

	@GetMapping("/videojuegos")
	@Operation(summary = "Obtener videojuegos", description = "Devuelve una lista de todos los videojuegos registrados en el inventario.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de videojuegos obtenida con éxito"),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<List<VideoJuego>> getVideoJuegos() {
	    List<VideoJuego> videojuegos = inventarioService.getVideoJuegos();
	    return new ResponseEntity<>(videojuegos, HttpStatus.OK);
	}

	@GetMapping("/objetos")
	@Operation(summary = "Obtener objetos", description = "Devuelve una lista de todos los objetos registrados en el inventario.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de objetos obtenida con éxito"),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<List<Objeto>> getObjetos() {
	    List<Objeto> objetos = inventarioService.getObjetos();
	    return new ResponseEntity<>(objetos, HttpStatus.OK);
	}

	@GetMapping("/puestos")
	@Operation(summary = "Obtener puestos", description = "Devuelve una lista de todos los puestos registrados en el inventario.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de puestos obtenida con éxito"),
	    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	public ResponseEntity<List<Puesto>> getPuestos() {
	    List<Puesto> puestos = inventarioService.getPuestos();
	    return new ResponseEntity<>(puestos, HttpStatus.OK);
	}
	
}
