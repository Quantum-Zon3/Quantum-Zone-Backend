package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Puesto;
import com.example.Quantum_Zone_Backend.modelo.Consola;
@Repository
public class PuestoRepository {
	private final Map<String, Puesto> baseDeDatos = new HashMap<>();
	//Guardar puesto
	public Puesto save(Puesto puesto) {
		baseDeDatos.put(puesto.getId(), puesto);
		return puesto;
	}
	//Buscar todos los puestos
	public List<Puesto> findAll() {
		return new ArrayList<>(baseDeDatos.values());
	}
	//Buscar puesto por id
	public Puesto findById(String id) {
		return baseDeDatos.get(id);
	}
	//Eliminar puesto por id
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	//Actualizar puesto
	public Puesto update(Puesto puesto) {
		if (baseDeDatos.containsKey(puesto.getId())) {
			baseDeDatos.put(puesto.getId(), puesto);
			return puesto;
		}
		return null;
	}
	//Buscar puesto por filtros
	public List<Puesto> findByFilters(String numeroDePuesto, Consola consola, int cantidadDeSillas, int cantidadDeControles) {
		return baseDeDatos.values().stream()
				.filter(consola1 -> numeroDePuesto == null || consola1.getNumeroDePuesto().equals(numeroDePuesto))
				.filter(consola1 -> consola == null || consola1.getConsola().equals(consola))
				.filter(consola1 -> cantidadDeSillas == 0 || consola1.getCantidadDeSillas() == cantidadDeSillas)
				.filter(consola1 -> cantidadDeControles == 0  || consola1.getCanditadDeControles() == cantidadDeControles)
				.collect(Collectors.toList());
		
	}
}
