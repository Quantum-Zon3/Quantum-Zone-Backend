package com.example.Quantum_Zone_Backend.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.repository.VideoJuegoRepository;
@Service
public class VideoJuegoService {
	private final VideoJuegoRepository videojuegoRepository;
	
	@Autowired
	public VideoJuegoService(VideoJuegoRepository videojuegoRepository) {
		this.videojuegoRepository = videojuegoRepository;
		// Inicializamos algunos datos
		initSampleData();
	}
		private void initSampleData() {
		// Crear videojuegos
		VideoJuego videojuego1 = new VideoJuego("The Last of Us Part II", "Acción/Aventura", "Naughty Dog", LocalDate.now(), "PS4");
		VideoJuego videojuego2 = new VideoJuego("God of War", "Acción/Aventura", "Santa Monica Studio", LocalDate.now(), "PS4");
		VideoJuego videojuego3 = new VideoJuego("Halo Infinite", "Disparos en primera persona", "343 Industries", LocalDate.now(), "Xbox Series X|S");
		
		// Guardar videojuegos en la base de datos
		save(videojuego1);
		save(videojuego2);
		save(videojuego3);
		
	}
		// guardar un videojuego
		public VideoJuego save(VideoJuego videojuego) {
			return videojuegoRepository.save(videojuego);
		}
		// encontrar un videojuego por id
		public VideoJuego findById(String id) {
			return videojuegoRepository.findById(id);
		}
		// listar todos los videojuegos
		public List<VideoJuego> findAll() {
			return videojuegoRepository.findAll();
		}
		// eliminar un videojuego por id
		public void deleteById(String id) {
			videojuegoRepository.deleteById(id);
		}
		// actualizar un videojuego
		public VideoJuego update(VideoJuego videojuego) {
			return videojuegoRepository.update(videojuego);
		}
		public List<VideoJuego> findByFilters(String nombre, String genero, String desarrollador) {
			return videojuegoRepository.findByFilters(nombre, genero, desarrollador);
		}
}
