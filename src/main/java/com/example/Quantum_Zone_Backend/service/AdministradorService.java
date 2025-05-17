package com.example.Quantum_Zone_Backend.service;
import com.example.Quantum_Zone_Backend.repository.AdministradorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.Administrador;
@Service
public class AdministradorService {
	private final AdministradorRepository administradorRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdministradorService(AdministradorRepository administradorRepository, PasswordEncoder passwordEncoder) {
		this.administradorRepository = administradorRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	// guardar un administrador
	public Administrador save(Administrador administrador) {
		// Encriptar la contraseña antes de guardarla
		administrador.setContraseña(passwordEncoder.encode(administrador.getContraseña()));
		return administradorRepository.save(administrador);
	}
	// encontrar un administrador por id
	public Optional<Administrador> findById(Integer id) {
		return administradorRepository.findById(id);
	}
	// listar todos los administradores
	public List<Administrador> findAll() {
		return administradorRepository.findAll();
	}
	// eliminar un administrador por id
	public boolean deleteById(Integer id) {
		return administradorRepository.deleteById(id);
	}
	// actualizar un administrador
	public Optional<Administrador> update(Integer id, Administrador administrador) {
		administrador.setContraseña(passwordEncoder.encode(administrador.getContraseña()));
		return administradorRepository.update(id, administrador);
	}
	// buscar administrador por filtros
	public Optional<List<Administrador>> findByFilters(String nombre) {
		return administradorRepository.findByFilters(nombre);
	}	
}
