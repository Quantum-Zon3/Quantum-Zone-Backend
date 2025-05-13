package com.example.Quantum_Zone_Backend.service;
import java.time.LocalDate;
import java.util.List;
import com.example.Quantum_Zone_Backend.repository.ClienteRepository;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.Cliente;
@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
		//Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
	}
	// guardar un cliente
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	// encontrar un cliente por id
	public Cliente findById(String id) {
		return clienteRepository.findById(id);
	}
	// listar todos los clientes
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	// eliminar un cliente por id
	public void deleteById(String id) {
		clienteRepository.deleteById(id);
	}
	// actualizar un cliente
	public Cliente update(Cliente cliente) {
		return clienteRepository.update(cliente);
	}
	// buscar cliente por filtros
	public List<Cliente> findByFilters(String nombre, int edad, String direccion, String imagen, String cedula , String telefono, LocalDate fechaRegistro, String email) {
		return clienteRepository.findByFilters(nombre, edad, direccion, imagen, cedula, telefono, fechaRegistro, email);
	}

}
