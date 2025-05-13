package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.repository.PuestoRepository;
import com.example.Quantum_Zone_Backend.modelo.Consola;
import com.example.Quantum_Zone_Backend.modelo.Puesto;
@Service
public class PuestoService {
	private final PuestoRepository puestoRepository;
	private final ConsolaService consolaService;
	
	@Autowired
	public PuestoService(PuestoRepository puestoRepository, ConsolaService consolaService) {
		this.puestoRepository = puestoRepository;
		this.consolaService = consolaService;
	}
	
	// guardar un puesto
	public Puesto save(Puesto puesto) {
		return puestoRepository.save(puesto);
	}
	// encontrar un puesto por id
	public Optional<Puesto> findById(Integer id) {
		return puestoRepository.findById(id);
	}
	// listar todos los puestos
	public List<Puesto> findAll() {
		return puestoRepository.findAll();
	}
	// eliminar un puesto por id
	public void deleteById(Integer id) {
		puestoRepository.deleteById(id);
	}
	// actualizar un puesto
	public Optional<Puesto> update(Integer id,Puesto puesto) {
		return puestoRepository.update(id, puesto);
	}
	// buscar puesto por filtros
	public Optional<List<Puesto>> findByFilters(String numeroDePuesto) {
		return puestoRepository.findByFilters(numeroDePuesto);
	}
	
}
