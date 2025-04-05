package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Puesto;
import com.example.Quantum_Zone_Backend.modelo.Consola;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.modelo.Objeto;
import com.example.Quantum_Zone_Backend.modelo.Inventario;
@Repository
public class InventarioRepository {
	private final Map<String, Inventario> baseDeDatos = new HashMap<>();
	//Guardar inventario
	public Inventario save(Inventario inventario) {
		baseDeDatos.put(inventario.getId(), inventario);
		return inventario;
	}
	//Buscar todos los inventarios
	public List<Inventario> findAll() {
		return new ArrayList<>(baseDeDatos.values());
	}
	//Buscar inventario por id
	public Inventario findById(String id) {
		return baseDeDatos.get(id);
	}
	//Eliminar inventario por id
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	//Actualizar inventario
	public Inventario update(Inventario inventario) {
		if (baseDeDatos.containsKey(inventario.getId())) {
			baseDeDatos.put(inventario.getId(), inventario);
			return inventario;
		}
		return null;
	}
	//Buscar inventario por filtros
	public List<Inventario> findByFilters(Map<String, Consola> consolas, Map<String, VideoJuego> juegos,
			Map<String, Objeto> objetos, Map<String, Puesto> puestos) {
		return baseDeDatos.values().stream()
				.filter(inventario -> consolas == null || inventario.getConsolas().equals(consolas))
				.filter(inventario -> juegos == null || inventario.getJuegos().equals(juegos))
				.filter(inventario -> objetos == null || inventario.getObjetos().equals(objetos))
				.filter(inventario -> puestos == null || inventario.getPuestos().equals(puestos))
				.collect(Collectors.toList());
		
	}
}
