package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.repository.PuestoRepository;
import com.example.Quantum_Zone_Backend.modelo.Consola;
import com.example.Quantum_Zone_Backend.modelo.Puesto;
@Service
public class PuestoService {
	private final PuestoRepository puestoRepository;
	private final ConsolaService consolaService;
	private final InventarioService inventarioService;
	
	@Autowired
	public PuestoService(PuestoRepository puestoRepository, ConsolaService consolaService, InventarioService inventarioService) {
		this.puestoRepository = puestoRepository;
		this.consolaService = consolaService;
		this.inventarioService = inventarioService;
		// Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
		List<Consola> consolas = consolaService.findAll();
			Puesto puesto = new Puesto("Puesto " + 1, consolas.get(0), 2, 2);
			Puesto puesto2 = new Puesto("Puesto " + 2, consolas.get(1), 2, 2);
			Puesto puesto3 = new Puesto("Puesto " + 3, consolas.get(2), 2, 2);
			save(puesto2);
			save(puesto3);
			save(puesto);
		
		
	}
	// guardar un puesto
	public Puesto save(Puesto puesto) {
		inventarioService.savePuesto(puesto);
		return puestoRepository.save(puesto);
	}
	// encontrar un puesto por id
	public Puesto findById(String id) {
		return puestoRepository.findById(id);
	}
	// listar todos los puestos
	public List<Puesto> findAll() {
		return puestoRepository.findAll();
	}
	// eliminar un puesto por id
	public void deleteById(String id) {
		puestoRepository.deleteById(id);
		inventarioService.deletePuesto(id);
	}
	// actualizar un puesto
	public Puesto update(Puesto puesto) {
		inventarioService.updatePuesto(puesto);
		return puestoRepository.update(puesto);
	}
	// buscar puesto por filtros
	public List<Puesto> findByFilters(String numeroDePuesto, Consola consola, int cantidadDeSillas, int cantidadDeControles) {
		return puestoRepository.findByFilters(numeroDePuesto, consola, cantidadDeSillas, cantidadDeControles);
	}
	
}
