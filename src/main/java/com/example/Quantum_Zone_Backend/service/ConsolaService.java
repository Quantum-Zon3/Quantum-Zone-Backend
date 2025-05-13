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
	public ConsolaService(ConsolaRepository consolaRepository) {
		this.consolaRepository = consolaRepository;
		
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
	public List<Consola> findByFilters(String marca, String consola, LocalDate fechaDePublicacion) {
		return consolaRepository.findByFilters(marca, consola, fechaDePublicacion);
	}
	// buscar consola por nombre
	public List<Consola> findByNombre(String nombre) {
		return consolaRepository.findByNombre(nombre);
	}

}
