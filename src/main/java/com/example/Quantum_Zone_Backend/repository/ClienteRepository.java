package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Cliente;
import java.time.LocalDate;
@Repository
public class ClienteRepository {
	private final Map<String, Cliente> baseDeDatos = new HashMap<>();
	//Guardar cliente
	public Cliente save(Cliente cliente) {
		baseDeDatos.put(cliente.getId(), cliente);
		return cliente;
	}
	//Buscar todos los clientes
	public List<Cliente> findAll() {
		return new ArrayList<>(baseDeDatos.values());
	}
	//Buscar cliente por id
	public Cliente findById(String id) {
		return baseDeDatos.get(id);
	}
	//Eliminar cliente por id
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	//Actualizar cliente
	public Cliente update(Cliente cliente) {
		if (baseDeDatos.containsKey(cliente.getId())) {
			baseDeDatos.put(cliente.getId(), cliente);
			return cliente;
		}
		return null;
	}
	//Buscar cliente por filtros
	public List<Cliente> findByFilters(String nombre, String edad, String direccion, String imagen, String cedula, String telefono, LocalDate fechaRegistro, String email) {
		return baseDeDatos.values().stream()
				.filter(cliente -> nombre == null || cliente.getNombre().equalsIgnoreCase(nombre))
				.filter(cliente -> cliente.getEdad() == 0)
				.filter(cliente -> direccion == null || cliente.getDireccion().equalsIgnoreCase(direccion))
				.filter(cliente -> imagen == null || cliente.getImagen().equalsIgnoreCase(imagen))
				.filter(cliente -> cedula == null || cliente.getCedula().equalsIgnoreCase(cedula))
				.filter(cliente -> telefono == null || cliente.getTelefono().equalsIgnoreCase(telefono))
				.filter(cliente -> fechaRegistro == null || cliente.getFechaRegistro().equals(fechaRegistro))
				.filter(cliente -> email == null || cliente.getEmail().equalsIgnoreCase(email))
				.collect(Collectors.toList());
		
	}
}
