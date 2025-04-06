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

import com.example.Quantum_Zone_Backend.modelo.Consola;
import com.example.Quantum_Zone_Backend.modelo.Puesto;
import com.example.Quantum_Zone_Backend.service.PuestoService;

@RestController
@RequestMapping("/quantumZone/puestos")
public class PuestoController {

	private final PuestoService puestoService;
	
	@Autowired
	public PuestoController(PuestoService puestoService) {
		this.puestoService = puestoService;
	}
	
	//Obtener todos los puestos
	@GetMapping
	public ResponseEntity<List<Puesto>> getAllPuestos() {
		List<Puesto> puestos = puestoService.findAll();
		return new ResponseEntity<>(puestos, HttpStatus.OK);
	}
	
	//Obtener puesto por id
	@GetMapping("/{id}")
	public ResponseEntity<Puesto> getPuestoById(@PathVariable String id) {
		Puesto puesto = puestoService.findById(id);
		if (puesto != null) {
			return new ResponseEntity<>(puesto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Crear puesto
	@PostMapping
	public ResponseEntity<Puesto> createPuesto(@RequestBody Puesto puesto) {
		Puesto nuevoPuesto = puestoService.save(puesto);
		return new ResponseEntity<>(nuevoPuesto, HttpStatus.CREATED);
	}
	//Actualizar puesto
	@PutMapping("/{id}")
	public ResponseEntity<Puesto> updatePuesto(@PathVariable String id, @RequestBody Puesto puesto) {
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
	public ResponseEntity<Void> deletePuesto(@PathVariable String id) {
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
	public ResponseEntity<List<Puesto>> getPuestos(
			@RequestParam(required = false) String numeroDePuesto,
			@RequestParam(required = false) Consola consola,
			@RequestParam(required = false) int cantidadDeSillas,
			@RequestParam(required = false) int canditadDeControles) {
		
		List<Puesto> puestosFiltrados = puestoService.findByFilters(numeroDePuesto, consola, cantidadDeSillas, canditadDeControles);
		return new ResponseEntity<>(puestosFiltrados, HttpStatus.OK);
	}
}
