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
	
	@Autowired
	public PuestoService(PuestoRepository puestoRepository, ConsolaService consolaService) {
		this.puestoRepository = puestoRepository;
		this.consolaService = consolaService;
		// Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
		List<Consola> consolas = consolaService.findAll();
		for (int i = 0; i <consolas.size() ; i++) {
			Puesto puesto = new Puesto("Puesto "+ i, consolas.get(i), 4, 2);
			save(puesto);
		}
		
		
	}
	// guardar un puesto
	public Puesto save(Puesto puesto) {
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
	}
	// actualizar un puesto
	public Puesto update(Puesto puesto) {
		return puestoRepository.update(puesto);
	}
	// buscar puesto por filtros
	public List<Puesto> findByFilters(String numeroDePuesto, Consola consola, int cantidadDeSillas, int cantidadDeControles) {
		return puestoRepository.findByFilters(numeroDePuesto, consola, cantidadDeSillas, cantidadDeControles);
	}
	
}
