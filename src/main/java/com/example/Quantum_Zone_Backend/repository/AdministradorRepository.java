package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Administrador;

@Repository
public class AdministradorRepository {
	private final Map<String, Administrador> baseDeDatos = new HashMap<>();
	//Guardar Administrador
	public Administrador save(Administrador administrador) {
		baseDeDatos.put(administrador.getId(), administrador);
		return administrador;
	}
	//Buscar todos los Administradores
	public List<Administrador> findAll() {
		return new ArrayList<>(baseDeDatos.values());
	}
	//Buscar administrador por id
	public Administrador findById(String id) {
		return baseDeDatos.get(id);
	}
	//Eliminar administrador por id
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	//Actualizar administrador
	public Administrador update(Administrador administrador) {
		if (baseDeDatos.containsKey(administrador.getId())) {
			baseDeDatos.put(administrador.getNombre(), administrador);
			baseDeDatos.put(administrador.getCedula(), administrador);
			baseDeDatos.put(administrador.getContraseña(), administrador);
			baseDeDatos.put(administrador.getEdad(), administrador);
			return administrador;
		}
		return null;
		
	}
	//Buscar administrador por filtros
	public List<Administrador> buscarPorFiltros(String nombre, String cedula, String edad) {
		return baseDeDatos.values().stream()
				.filter(administrador -> nombre == null || administrador.getNombre().equalsIgnoreCase(nombre))
				.filter(administrador -> cedula == null || administrador.getCedula().equalsIgnoreCase(cedula))
				.filter(administrador -> edad == null || administrador.getEdad().equalsIgnoreCase(edad))
				.filter(administrador -> administrador.getContraseña() == null || administrador.getContraseña().equalsIgnoreCase(administrador.getContraseña()))
				.collect(Collectors.toList());
		
	}
}
