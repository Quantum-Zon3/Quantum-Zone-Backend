package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Consola;


@Repository
public class ConsolaRepository {
	 private final Map<String, Consola> baseDeDatos = new HashMap<>();
	 //Guardar consola
	 public Consola save(Consola consola) {
	        baseDeDatos.put(consola.getMarca(), consola);
	        baseDeDatos.put(consola.getConsola(), consola);
	        baseDeDatos.put(consola.getFechaDePublicacion(), consola);
	        
	        return consola;
	    }
	 //Buscar consola
	 public List<Consola> findAll() {
	        return new ArrayList<>(baseDeDatos.values());
	    }
	 //Buscar consola por id
	 public Consola findById(String id) {
	        return baseDeDatos.get(id);
	    }
	 //Eliminar consola por id
	 public void deleteById(String id) {
		 baseDeDatos.remove(id);
	 }
	 //Actualizar consola
	 public Consola update(Consola consola) {
	        if (baseDeDatos.containsKey(consola.getId())) {
	            baseDeDatos.put(consola.getMarca(), consola);
	            baseDeDatos.put(consola.getConsola(), consola);
	            baseDeDatos.put(consola.getFechaDePublicacion(), consola);
	            return consola;
	        }
	        return null;
	    }
	 //Buscar consola por filtros
	 public List<Consola> buscarPorFiltros(String marca, String consola, String fechaDePublicacion) {
		 return baseDeDatos.values().stream()
				 .filter(consola1 -> consola1.getMarca().equalsIgnoreCase(marca))
				 .filter(consola1 -> consola1.getConsola().equalsIgnoreCase(consola))
				 .filter(consola1 -> consola1.getFechaDePublicacion().equalsIgnoreCase(fechaDePublicacion))
				 .collect(Collectors.toList());
		 
	 }
}
