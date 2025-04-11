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
	
	@Autowired
	public ObjetoController(ObjetoService objetoService) {
		this.objetoService = objetoService;
	}
	
	@GetMapping
	@Operation(summary = "Obtener todos los objetos", description = "Devuelve una lista de todos los objetos registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de objetos obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<Objeto>> getAllObjetos() {
		List<Objeto> objetos = objetoService.findAll();
		return new ResponseEntity<>(objetos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obtener objeto por ID", description = "Devuelve un objeto específico basado en su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Objeto encontrado"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado")
	})
	public ResponseEntity<Objeto> getObjetoById(@PathVariable @Parameter(description = "ID del objeto") String id) {
		Objeto objeto = objetoService.findById(id);
		if (objeto != null) {
			return new ResponseEntity<>(objeto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
	public ResponseEntity<Objeto> updateObjeto(@PathVariable @Parameter(description = "ID del objeto") String id, @RequestBody Objeto objeto) {
		Objeto objetoViejito = objetoService.findById(id);
		if (objetoViejito != null) {
			objeto.setId(id);
			Objeto objetoActualizado = objetoService.update(objeto);
			return new ResponseEntity<>(objetoActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar objeto", description = "Elimina un objeto existente basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Objeto eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado") })
	public ResponseEntity<Void> deleteObjeto(@PathVariable @Parameter(description = "ID del objeto") String id) {
		Objeto objeto = objetoService.findById(id);
		if (objeto != null) {
			objetoService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/filtros")
	@Operation(summary = "Buscar objetos por filtros", description = "Devuelve una lista de objetos que coinciden con los filtros proporcionados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de objetos filtrados obtenida con éxito"),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta") })
	public ResponseEntity<List<Objeto>> getObjetosByFilters(
			@RequestParam(required = false) @Parameter(description = "nombre") String nombre,
	        @RequestParam(required = false) @Parameter(description = "descripción") String descripcion,
	        @RequestParam(required = false) @Parameter(description = "fecha") LocalDate fecha,
	        @RequestParam(required = false) @Parameter(description = "estado") String estado,
	        @RequestParam(required = false) @Parameter(description = "categoría") String categoria) {
		List<Objeto> objetosFiltrados = objetoService.findByFilters(nombre, descripcion, fecha, estado, categoria);
		if (objetosFiltrados.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(objetosFiltrados, HttpStatus.OK);
		}
	}	
	

}
