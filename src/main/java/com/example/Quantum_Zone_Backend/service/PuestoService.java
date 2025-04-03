package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.repository.PuestoRepository;
import com.example.Quantum_Zone_Backend.modelo.Consola;
import com.example.Quantum_Zone_Backend.modelo.Puesto;
@Service
public class PuestoService {
	private final PuestoRepository puestoRepository;
	
	@Autowired
	public PuestoService(PuestoRepository puestoRepository) {
		this.puestoRepository = puestoRepository;
		// Inicializamos algunos datos
		initSampleData();
	}
	private void initSampleData() {
		// Crear Puestos
		Consola consola1 = new Consola("PlayStation 5", "2020-11-12", "La PlayStation 5 es una consola de videojuegos de última generación desarrollada por Sony.", "Sony", LocalDate.now());
		Consola consola2 = new Consola("Xbox Series X", "2020-11-10", "La Xbox Series X es una consola de videojuegos de última generación desarrollada por Microsoft.", "Microsoft", LocalDate.now());
		Consola consola3 = new Consola("Nintendo Switch", "2017-03-03", "La Nintendo Switch es una consola de videojuegos híbrida desarrollada por Nintendo.", "Nintendo", LocalDate.now());

		Puesto puesto1 = new Puesto("Puesto 1", consola1, 4, 2);
		Puesto puesto2 = new Puesto("Puesto 2", consola2, 6, 4);
		Puesto puesto3 = new Puesto("Puesto 3", consola3, 3, 2);
		Puesto puesto4 = new Puesto("Puesto 4", consola1, 5, 3);
		Puesto puesto5 = new Puesto("Puesto 5", consola2, 4, 4);
		
		// Guardar puestos en la base de datos
		save(puesto1);
		save(puesto2);
		save(puesto3);
		
	}
	// guardar un puesto
	public Puesto save(Puesto puesto) {
		return puestoRepository.save(puesto);
	}
	// encontrar un puesto por id
	public Puesto findById(String id) {
		return puestoRepository.findById(id).orElse(null);
	}
	// listar todos los puestos
	public List<Puesto> findAll() {
		return puestoRepository.findAll();
	}
	// eliminar un puesto por id
	public void deleteById(String id) {
		puestoRepository.deleteById(id);
	}
	// actualizar un puesto
	public Puesto update(Puesto puesto) {
		return puestoRepository.update(puesto);
	}
	// buscar puesto por filtros
	public List<Puesto> findByFilters(String nombre, String ubicacion, String descripcion) {
		return puestoRepository.findByFilters(nombre, ubicacion, descripcion);
	}
	
}
