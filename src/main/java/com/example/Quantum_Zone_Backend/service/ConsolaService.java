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
	@Autowired
	public ConsolaService(ConsolaRepository consolaRepository ) {
		this.consolaRepository = consolaRepository;
		// Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
		// Crear Consolas
		Consola consola1 = new Consola("PlayStation 5", "2020-11-12", "La PlayStation 5 es una consola de videojuegos de última generación desarrollada por Sony.", "Sony", LocalDate.now());
		Consola consola2 = new Consola("Xbox Series X", "2020-11-10", "La Xbox Series X es una consola de videojuegos de última generación desarrollada por Microsoft.", "Microsoft", LocalDate.now());
		Consola consola3 = new Consola("Nintendo Switch", "2017-03-03", "La Nintendo Switch es una consola de videojuegos híbrida desarrollada por Nintendo.", "Nintendo", LocalDate.now());
		
		// Guardar consolas en la base de datos
		save(consola1);
		save(consola2);
		save(consola3);
		
	}
	// guardar una consola
	public Consola save(Consola consola) {
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
	}
	// actualizar una consola
	public Consola update(Consola consola) {
		return consolaRepository.update(consola);
	}
	// buscar consola por filtros
	public List<Consola> findByFilters(String marca, String consola, String fechaDePublicacion) {
		return consolaRepository.findByFilters(marca, consola, fechaDePublicacion);
	}

}
