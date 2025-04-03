package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import com.example.Quantum_Zone_Backend.repository.ClienteRepository;
import java.util.Map;
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
		// Crear Clientes
		Cliente cliente1 = new Cliente("Juan Perez", 30, "123 Main St", "imagen1.jpg", "555-1234", "2023-01-01", "juan.perez@example.com");
		Cliente cliente2 = new Cliente("Maria Gomez", 25, "456 Elm St", "imagen2.jpg", "555-5678", "2023-02-01", "maria.gomez@example.com");
		Cliente cliente3 = new Cliente("Carlos Ruiz", 40, "789 Oak St", "imagen3.jpg", "555-8765", "2023-03-01", "carlos.ruiz@example.com");
		Cliente cliente4 = new Cliente("Ana Lopez", 35, "321 Pine St", "imagen4.jpg", "555-4321", "2023-04-01", "ana.lopez@example.com");
		Cliente cliente5 = new Cliente("Luis Martinez", 28, "654 Maple St", "imagen5.jpg", "555-6789", "2023-05-01", "luis.martinez@example.com");

		// Guardar clientes en la base de datos
		save(cliente1);
		save(cliente2);
		save(cliente3);
		save(cliente4);
		save(cliente5);
	
	}
	// guardar un cliente
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	// encontrar un cliente por id
	public Cliente findById(String id) {
		return clienteRepository.findById(id).orElse(null);
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
	public List<Cliente> findByFilters(String nombre, int edad, String direccion, String imagen, String telefono, String fechaRegistro, String email) {
		return clienteRepository.findByFilters(nombre, edad, direccion, imagen, telefono, fechaRegistro, email);
	}

}
