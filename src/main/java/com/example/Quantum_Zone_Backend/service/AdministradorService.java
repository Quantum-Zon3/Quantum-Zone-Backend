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
		//Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
		// Crear Administradores
		Administrador carlosLopez = new Administrador("Carlos Andres Lopez Perez", "1234", 19, "1115574807");
		Administrador miguelAngel = new Administrador("Miguel Angel Lievano Buitrago ", "1234", 18, "1107843533");
		Administrador carlosMario = new Administrador("Carlos Mario Patino Ramirez", "1234", 18, "1094897822");
		// Guardar adminstradores en la base de datos
		save(carlosLopez);
		save(miguelAngel);
		save(carlosMario);	
	}
	// guardar un administrador
	public Administrador save(Administrador administrador) {
		return administradorRepository.save(administrador);
	}
	// encontrar un administrador por id
	public Administrador findById(String id) {
		return administradorRepository.findById(id).orElse(null);
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
