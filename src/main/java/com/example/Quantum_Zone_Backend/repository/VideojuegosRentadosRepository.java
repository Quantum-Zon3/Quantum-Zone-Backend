package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Cliente;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.modelo.VideojuegoRentado;
import java.time.LocalDate;
@Repository
public class VideojuegosRentadosRepository {
	private final Map<String, VideojuegoRentado> baseDeDatos = new HashMap<>();
	public VideojuegoRentado save(VideojuegoRentado videojuegoRentado) {
		baseDeDatos.put(videojuegoRentado.getId(),videojuegoRentado);
		return videojuegoRentado;
	}
	public List<VideojuegoRentado> findAll() {
	    return new ArrayList<>(baseDeDatos.values());
	}
	public VideojuegoRentado findById(String id) {
		return baseDeDatos.get(id);
	}
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	public VideojuegoRentado update(VideojuegoRentado videojuegoRentado) {
		if (baseDeDatos.containsKey(videojuegoRentado.getId())) {
			baseDeDatos.put(videojuegoRentado.getId(), videojuegoRentado);
			return videojuegoRentado;
		}
		return null;
	}
	public List<VideojuegoRentado> findByFilters(String id, LocalDate fechaInicio,LocalDate fechaFinal,String cedula,VideoJuego videojuego) {
		return baseDeDatos.values().stream()
				.filter(videojuegoRentado -> id == null || videojuegoRentado.getId().equals(id))
				.filter(videojuegoRentado -> fechaInicio == null || videojuegoRentado.getFechaAlquiler().equals(fechaInicio))
				.filter(videojuegoRentado -> fechaFinal == null || videojuegoRentado.getFechaDevolucion().equals(fechaFinal))
				.filter(videojuegoRentado -> cedula == null || videojuegoRentado.getCliente().getCedula().equals(cedula))
				.filter(videojuegoRentado -> videojuego == null || videojuegoRentado.getVideojuego().equals(videojuego))
				.collect(Collectors.toList());
	}
}
