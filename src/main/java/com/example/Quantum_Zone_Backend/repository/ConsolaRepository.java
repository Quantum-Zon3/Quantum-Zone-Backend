package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Consola;
import java.time.LocalDate;

@Repository
public class ConsolaRepository {
	 private final Map<String, Consola> baseDeDatos = new HashMap<>();
	 //Guardar consola
	 public Consola save(Consola consola) {
	        baseDeDatos.put(consola.getId(), consola);
	        
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
	            baseDeDatos.put(consola.getId(), consola);
	            return consola;
	        }
	        return null;
	    }
	 //Buscar consola por filtros
	 public List<Consola> findByFilters(String marca, String consola, LocalDate fechaDePublicacion) {
		 return baseDeDatos.values().stream()
				 .filter(consola1 -> marca == null || consola1.getMarca().equalsIgnoreCase(marca))
				 .filter(consola1 -> consola == null || consola1.getConsola().equalsIgnoreCase(consola))
				 .filter(consola1 -> fechaDePublicacion == null || consola1.getFechaDePublicacion().equals(fechaDePublicacion))
				 .collect(Collectors.toList());
		 
	 }
	 public List<Consola> findByNombre(String nombre) {
	        return baseDeDatos.values().stream()
	                .filter(consola -> nombre == null || consola.getConsola().equalsIgnoreCase(nombre))
	                .collect(Collectors.toList());
	    }
}
