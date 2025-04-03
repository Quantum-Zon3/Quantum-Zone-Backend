package com.example.Quantum_Zone_Backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class VideojuegoRepository {
	private final Map<String, Videojuego> baseDeDatos = new HashMap<>();
	public Videojuego save(videojuego videojuego) {
        baseDeDatos.put(videojuego.getId(),videojuego);
        return videojuego;
    }
	public List<Videojuego> findAll() {
	    return new ArrayList<>(baseDeDatos.values());
	}
	public Videojuego findById(String id) {
        return baseDeDatos.get(id);
    }
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	public Videojuego update(Videojuego videojuego) {
        if (baseDeDatos.containsKey(videojuego.getId())) {
            baseDeDatos.put(videojuego.getId(), videojuego);
            return videojuego;
        }
        return null;
    }
	public List<Videojuego> findByNombre(String nombre) {
		return baseDeDatos.values().stream()
				.filter(videojuego -> videojuego.getNombre().equalsIgnoreCase(nombre))
				.filter(videojuego -> videojuego.getFechaDePublicacion().equalsIgnoreCase(fechaDePublicacion))
				.filter(videojuego -> videojuego.getPublico().equalsIgnoreCase(publico))
				.filter(videojuego -> videojuego.getTipo().equalsIgnoreCase(tipo))
				.collect(Collectors.toList());
	}
}
