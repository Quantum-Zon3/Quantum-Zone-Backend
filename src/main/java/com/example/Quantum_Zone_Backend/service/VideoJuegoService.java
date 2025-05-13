package com.example.Quantum_Zone_Backend.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		
	}
		
		// guardar un videojuego
		public VideoJuego save(VideoJuego videojuego) {
			return videojuegoRepository.save(videojuego);
		}
		
		// encontrar un videojuego por id
		public Optional<VideoJuego> findById(Integer id) {
			return videojuegoRepository.findById(id);
		}
		// listar todos los videojuegos
		public List<VideoJuego> findAll() {
			return videojuegoRepository.findAll();
		}
		// eliminar un videojuego por id
		public void deleteById(Integer id) {
			videojuegoRepository.deleteById(id);
		}
		// actualizar un videojuego
		public Optional<VideoJuego> update(Integer id, VideoJuego videojuego) {
			return videojuegoRepository.update(id,videojuego);
		}
		public Optional<List<VideoJuego>> findByFilters(String nombre) {
			return videojuegoRepository.findByFilters(nombre);
		}
}
