package com.example.Quantum_Zone_Backend.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import java.time.LocalDate;
@Repository
public class VideoJuegoRepository {
	private final Map<String, VideoJuego> baseDeDatos = new HashMap<>();
	public VideoJuego save(VideoJuego videojuego) {
        baseDeDatos.put(videojuego.getId(),videojuego);
        return videojuego;
    }
	public List<VideoJuego> findAll() {
	    return new ArrayList<>(baseDeDatos.values());
	}
	public VideoJuego findById(String id) {
        return baseDeDatos.get(id);
    }
	public void deleteById(String id) {
		baseDeDatos.remove(id);
	}
	public VideoJuego update(VideoJuego videojuego) {
        if (baseDeDatos.containsKey(videojuego.getId())) {
            baseDeDatos.put(videojuego.getId(), videojuego);
            return videojuego;
        }
        return null;
    }
	public List<VideoJuego> findByFilters(String nombre, LocalDate fechaDePublicacion,String descripcion, String publico, String tipo) {
		return baseDeDatos.values().stream()
				.filter(videojuego -> nombre == null || videojuego.getNombre().equalsIgnoreCase(nombre))
				.filter(videojuego -> fechaDePublicacion == null || videojuego.getFechaDePubliacion().equals(fechaDePublicacion))
				.filter(videojuego -> publico == null || videojuego.getPublico().equalsIgnoreCase(publico))
				.filter(videojuego -> tipo == null || videojuego.getTipo().equalsIgnoreCase(tipo))
				.filter(videojuego -> descripcion == null || videojuego.getDescripcion().equalsIgnoreCase(descripcion))
				.collect(Collectors.toList());
	}
}
