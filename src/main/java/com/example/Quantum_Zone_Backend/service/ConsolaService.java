package com.example.Quantum_Zone_Backend.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public Optional<Consola> findById(Integer id) {
		return consolaRepository.findById(id);
	}
	// listar todas las consolas
	public List<Consola> findAll() {
		return consolaRepository.findAll();
	}
	// eliminar una consola por id
	public boolean deleteById(Integer id) {
		return consolaRepository.deleteById(id);
	}
	// actualizar una consola
	public Optional<Consola> update(Integer id, Consola consola) {
		return consolaRepository.update(id, consola);
	}
	// buscar consola por filtros
	public Optional<List<Consola>> findByFilters(String consola) {
		return consolaRepository.findByFilters(consola);
	}
}
