package com.example.Quantum_Zone_Backend.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.repository.ConsolaRepository;
import com.example.Quantum_Zone_Backend.modelo.Consola;
@Service
public class ConsolaService {
	private final ConsolaRepository consolaRepository;
	private final InventarioService inventarioService;
	@Autowired
	public ConsolaService(ConsolaRepository consolaRepository,InventarioService inventarioService) {
		this.consolaRepository = consolaRepository;
		this.inventarioService = inventarioService;
		// Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
		// Crear Consolas
		Consola consola1 = new Consola("Sony", "PlayStation 5", LocalDate.now());
		Consola consola2 = new Consola("Microsoft", "Xbox Series X", LocalDate.now());
		Consola consola3 = new Consola("Nintendo", "Nintendo Switch", LocalDate.now());
		
		// Guardar consolas en la base de datos
		save(consola1);
		save(consola2);
		save(consola3);
		
	}
	// guardar una consola
	public Consola save(Consola consola) {
		inventarioService.saveConsola(consola);
		return consolaRepository.save(consola);
	}
	// encontrar una consola por id
	public Consola findById(String id) {
		return consolaRepository.findById(id);
	}
	// listar todas las consolas
	public List<Consola> findAll() {
		return consolaRepository.findAll();
	}
	// eliminar una consola por id
	public void deleteById(String id) {
		consolaRepository.deleteById(id);
		inventarioService.deleteConsola(id);
	}
	// actualizar una consola
	public Consola update(Consola consola) {
		inventarioService.updateConsola(consola);
		return consolaRepository.update(consola);
	}
	// buscar consola por filtros
	public List<Consola> findByFilters(String marca, String consola, LocalDate fechaDePublicacion) {
		return consolaRepository.findByFilters(marca, consola, fechaDePublicacion);
	}
	// buscar consola por nombre
	public List<Consola> findByNombre(String nombre) {
		return consolaRepository.findByNombre(nombre);
	}

}
