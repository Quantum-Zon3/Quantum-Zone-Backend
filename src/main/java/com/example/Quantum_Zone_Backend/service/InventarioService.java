package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.*;
import com.example.Quantum_Zone_Backend.repository.InventarioRepository;
@Service
public class InventarioService {
	@Autowired
	private final InventarioRepository inventarioRepository;
	private final Inventario inventario;
	
	public InventarioService(InventarioRepository inventarioRepository) {
		this.inventarioRepository = inventarioRepository;
		Map<String, Consola> consolas = new HashMap<>();
		Map<String, VideoJuego> juegos = new HashMap<>();
		Map<String, Objeto> objetos = new HashMap<>();
		Map<String, Puesto> puestos = new HashMap<>();
		this.inventario = new Inventario(consolas,juegos,objetos,puestos);
		
		// Inicializamos algunos datos
		initSampleData();
	}
	
	private void initSampleData() {
		
	}
	public Map<String, Consola> saveConsola(Consola consola) {
		Map<String, Consola> consolas = this.inventario.getConsolas();
		consolas.put(consola.getId(), consola);
		return consolas;
	}
	public Map<String, VideoJuego> saveVideoJuego(VideoJuego videojuego) {
		Map<String, VideoJuego> videoJuegos = this.inventario.getJuegos();
		videoJuegos.put(videojuego.getId(), videojuego);
		return videoJuegos;
	}
	public Map<String, Objeto> saveObjeto(Objeto objeto) {
		Map<String, Objeto> objetos = this.inventario.getObjetos();
		objetos.put(objeto.getId(), objeto);
		return objetos;
	}
	public Map<String, Puesto> savePuesto(Puesto puesto) {
		Map<String, Puesto> puestos = this.inventario.getPuestos();
		puestos.put(puesto.getId(), puesto);
		return puestos;
	}
	
	public Inventario save(Inventario inventario) {
		return inventarioRepository.save(inventario);
	}
	
	public Inventario findById(String id) {
		return inventarioRepository.findById(id);
	}
	
	public List<Inventario> findAll() {
		return inventarioRepository.findAll();
	}
	
	public void deleteById(String id) {
		inventarioRepository.deleteById(id);
	}
	
	public Inventario update(Inventario inventario) {
		return inventarioRepository.update(inventario);
	}
	
	public List<Inventario> findByFilters(Map<String,Consola> consolas, Map<String,VideoJuego> videoJuegos, Map<String , Objeto> objetos, Map<String, Puesto> puestos) {
		return inventarioRepository.findByFilters(consolas, videoJuegos, objetos, puestos);
	}

}
