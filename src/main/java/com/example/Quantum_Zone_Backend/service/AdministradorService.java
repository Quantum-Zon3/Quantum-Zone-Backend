package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import com.example.Quantum_Zone_Backend.repository.AdministradorRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.Administrador;
@Service
public class AdministradorService {
	private final AdministradorRepository administradorRepository;
	
	@Autowired
	public AdministradorService(AdministradorRepository administradorRepository) {
		this.administradorRepository = administradorRepository;
	}
	
	// guardar un administrador
	public Administrador save(Administrador administrador) {
		return administradorRepository.save(administrador);
	}
	// encontrar un administrador por id
	public Administrador findById(String id) {
		return administradorRepository.findById(id);
	}
	// listar todos los administradores
	public List<Administrador> findAll() {
		return administradorRepository.findAll();
	}
	// eliminar un administrador por id
	public void deleteById(String id) {
		administradorRepository.deleteById(id);
	}
	// actualizar un administrador
	public Administrador update(Administrador administrador) {
		return administradorRepository.update(administrador);
	}
	// buscar administrador por filtros
	public List<Administrador> findByFilters(String nombre, String cedula, int edad) {
		return administradorRepository.findByFilters(nombre, cedula, edad);
	}	
}
