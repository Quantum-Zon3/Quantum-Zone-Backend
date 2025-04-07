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
	private final InventarioService inventarioService;
	
	@Autowired
	public VideoJuegoService(VideoJuegoRepository videojuegoRepository ,InventarioService inventarioService) {
		this.videojuegoRepository = videojuegoRepository;
		this.inventarioService = inventarioService;
		// Inicializamos algunos datos
		initSampleData();
	}
		private void initSampleData() {
		// Crear videojuegos
			VideoJuego videojuego1 = new VideoJuego("The Last of Us Part II", LocalDate.now(), "Un juego de acción y aventura", "Adultos", "Acción/Aventura");
		    VideoJuego videojuego2 = new VideoJuego("God of War", LocalDate.now(), "Un juego de acción y aventura mitológica", "Adultos", "Acción/Aventura");
		    VideoJuego videojuego3 = new VideoJuego("Halo Infinite", LocalDate.now(), "Un juego de disparos en primera persona", "Adolescentes", "Disparos en primera persona");
		
		// Guardar videojuegos en la base de datos
		save(videojuego1);
		save(videojuego2);
		save(videojuego3);
		
	}
		// guardar un videojuego
		public VideoJuego save(VideoJuego videojuego) {
			inventarioService.saveVideoJuego(videojuego);
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
		public List<VideoJuego> findByFilters(String nombre, LocalDate fechaDePubliacion, String descripcion, String publico, String tipo) {
			return videojuegoRepository.findByFilters(nombre, fechaDePubliacion, descripcion, publico, tipo);
		}
}
