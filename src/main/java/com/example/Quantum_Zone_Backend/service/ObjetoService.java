package com.example.Quantum_Zone_Backend.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.repository.ObjetoRepository;
import com.example.Quantum_Zone_Backend.modelo.Objeto;
@Service
public class ObjetoService {
	private final ObjetoRepository objetoRepository;

	@Autowired
	public ObjetoService(ObjetoRepository objetoRepository) {
		this.objetoRepository = objetoRepository;

	}
	
	public Objeto save(Objeto objeto) {
		return objetoRepository.save(objeto);
	}
	public Objeto findById(String id) {
		return objetoRepository.findById(id);
	}
	public List<Objeto> findAll() {
		return objetoRepository.findAll();
	}
	public void deleteById(String id) {
		objetoRepository.deleteById(id);	
	}
	public Objeto update(Objeto objeto) {
		return objetoRepository.update(objeto);
	}
	public List<Objeto> findByFilters(String nombre, String descripcion, LocalDate fecha, String estado, String categoria) {
		return objetoRepository.findByFilters(nombre, descripcion, fecha, estado, categoria);
	}
}
