package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Objeto;

@Repository
public class ObjetoRepository {
	private final Map<String, Objeto> baseDeDatos = new HashMap<>();
	//Guardar objeto
	public Objeto save(Objeto objeto) {
		baseDeDatos.put(objeto.getId(), objeto);
		return objeto;
	}
	//Buscar todos los objetos
	public List<Objeto> findAll() {
		return new ArrayList<>(baseDeDatos.values());
	}
	//Buscar objeto por id
	public Objeto findById(String id) {
		return baseDeDatos.get(id);
	}
	//Eliminar objeto por id
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	//Actualizar objeto
	public Objeto update(Objeto objeto) {
		if (baseDeDatos.containsKey(objeto.getId())) {
			baseDeDatos.put(objeto.getNombre(), objeto);
			baseDeDatos.put(objeto.getCategoria(), objeto);
			baseDeDatos.put(objeto.getFecha(), objeto);
			baseDeDatos.put(objeto.getEstado(), objeto);
			baseDeDatos.put(objeto.getDescripcion(), objeto);
			return objeto;
		}
		return null;
	}
	//Buscar objeto por filtros
	public List<Objeto> buscarPorFiltros(String nombre, String categoria, String descripcion) {
		return baseDeDatos.values().stream()
				.filter(objeto -> nombre == null || objeto.getNombre().equalsIgnoreCase(nombre))
				.filter(objeto -> categoria == null || objeto.getCategoria().equalsIgnoreCase(categoria))
				.filter(objeto -> descripcion == null || objeto.getDescripcion().equalsIgnoreCase(descripcion))
				.filter(objeto -> objeto.getFecha() == null || objeto.getFecha().equalsIgnoreCase(objeto.getFecha()))
				.filter(objeto -> objeto.getEstado() == null || objeto.getEstado().equalsIgnoreCase(objeto.getEstado()))
				.collect(Collectors.toList());
		
	}
}
