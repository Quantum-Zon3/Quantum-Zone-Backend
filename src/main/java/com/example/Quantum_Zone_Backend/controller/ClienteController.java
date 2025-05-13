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
import com.example.Quantum_Zone_Backend.service.ClienteService;
import com.example.Quantum_Zone_Backend.modelo.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/quantumZone/clientes")
@Tag(name = "Cliente", description = "Controlador de clientes")
public class ClienteController {
	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	@Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes registrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	
	public ResponseEntity<List<Cliente>> getAllClientes() {
		List<Cliente> clientes = clienteService.findAll();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un cliente específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "cliente no encontrado")
    })
    public ResponseEntity<Cliente> getClienteById(@PathVariable @Parameter(description = "ID del cliente") int id) {
		Cliente cliente = clienteService.findById(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Cliente> createCliente(@RequestBody @Parameter(description = "Datos del cliente a crear") Cliente cliente) {
    	Cliente newCliente = clienteService.save(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente", description = "Actualiza los datos de un cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> updateCliente(@PathVariable @Parameter(description = "ID del cliente") int id,
                                                 @RequestBody @Parameter(description = "Datos actualizados del cliente") Cliente cliente) {
        Cliente existingCliente = clienteService.findById(id);
        if (existingCliente != null) {
        	cliente.setId(id);
            Cliente updatedCliente = clienteService.update(cliente);
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Void> deleteCliente(@PathVariable @Parameter(description = "ID del cliente") int id) {
    	Cliente existingCliente = clienteService.findById(id);
        if (existingCliente != null) {
        	clienteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar cliente por filtros", description = "Busca clientes por nombre, email y edad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrados"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<List<Cliente>> buscarUsuarios(
            @RequestParam(required = false) @Parameter(description = "Nombre del cliente") String nombre,
            @RequestParam(defaultValue = "0")@Parameter(description = "Edad del cliente") int edad,
            @RequestParam(required = false) @Parameter(description = "Dirección del cliente") String direccion,
            @RequestParam(required = false) @Parameter(description = "Imagen del cliente") String imagen,
            @RequestParam(required = false) @Parameter(description = "Cédula del cliente") String cedula,
            @RequestParam(required = false) @Parameter(description = "Teléfono del cliente") String telefono,
            @RequestParam(required = false) @Parameter(description = "Fecha de registro del cliente") LocalDate fechaRegistro,
            @RequestParam(required = false) @Parameter(description = "Email del usuario") String email) {
        List<Cliente> clientes = clienteService.findByFilters(nombre, edad, direccion, imagen, cedula, telefono, fechaRegistro, email);
        if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

}
