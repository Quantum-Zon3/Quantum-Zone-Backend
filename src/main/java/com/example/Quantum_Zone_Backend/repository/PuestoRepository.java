package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Puesto;

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
	public List<Puesto> findByFilters(String numeroDePuesto, String consola, int cantidadDeSillas, int cantidadDeControles) {
		return baseDeDatos.values().stream()
				.filter(consola1 -> numeroDePuesto == null || consola1.getNumeroDePuesto().equalsIgnoreCase(numeroDePuesto))
				.filter(consola1 -> consola == null || consola1.getConsola().equalsIgnoreCase(consola))
				.filter(consola1 -> consola1.getCantidadDeSillas() == 0)
				.filter(consola1 -> consola1.getCanditadDeControles() == 0)
				.collect(Collectors.toList());
		
	}
}
