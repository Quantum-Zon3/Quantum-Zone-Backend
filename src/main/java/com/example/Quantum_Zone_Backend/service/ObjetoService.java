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
	private final InventarioService inventarioService;
	@Autowired
	public ObjetoService(ObjetoRepository objetoRepository , InventarioService inventarioService) {
		this.objetoRepository = objetoRepository;
		this.inventarioService = inventarioService;
		// Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
		// Crear objetos
		Objeto objeto1 = new Objeto("Control de PlayStation 5", "Control inalámbrico para PlayStation 5 con retroalimentación háptica y gatillos adaptativos.", LocalDate.now(), "Activo", "Categoria 1");
		Objeto objeto2 = new Objeto("Control de PlayStation 5", "Control adicional para PlayStation 5, ideal para juegos multijugador.", LocalDate.now(), "Inactivo", "Categoria 2");
		Objeto objeto3 = new Objeto("Control de XboxSeries", "Control inalámbrico para Xbox Series X|S con diseño ergonómico y batería de larga duración.", LocalDate.now(), "Activo", "Categoria 3");
		Objeto objeto4 = new Objeto("Control de XboxSeries", "Control adicional para Xbox Series X|S, perfecto para sesiones de juego prolongadas.", LocalDate.now(), "Inactivo", "Categoria 4");
		Objeto objeto5 = new Objeto("Control de PlayStation", "Control clásico de PlayStation con diseño mejorado y conectividad Bluetooth.", LocalDate.now(), "Activo", "Categoria 5");
		
		// Guardar objetos en la base de datos
		save(objeto1);
		save(objeto2);
		save(objeto3);
		save(objeto4);
		save(objeto5);
		
	}
	public Objeto save(Objeto objeto) {
		inventarioService.saveObjeto(objeto);
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
		inventarioService.deleteObjeto(id);
	}
	public Objeto update(Objeto objeto) {
		inventarioService.updateObjeto(objeto);
		return objetoRepository.update(objeto);
	}
	public List<Objeto> findByFilters(String nombre, String descripcion, LocalDate fecha, String estado, String categoria) {
		return objetoRepository.findByFilters(nombre, descripcion, fecha, estado, categoria);
	}
}
