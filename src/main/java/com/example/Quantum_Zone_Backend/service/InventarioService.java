package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Quantum_Zone_Backend.modelo.*;
public class InventarioService {
	@Autowired
	private final InventarioRepository inventarioRepository;
	private final PuestoService puestoService;
	private final ConsolaService consolaService;
	private final ObjetoService objetoService;
	private final VideoJuegoService videojuegoService;
	
	public InventarioService(InventarioRepository inventarioRepository,PuestoService puestoService,ConsolaService consolaService,ObjetoService objetoService,VideoJuegoService videojuegoService) {
		this.inventarioRepository = inventarioRepository;
		this.puestoService = puestoService;
		this.consolaService = consolaService;
		this.objetoService = objetoService;
		this.videojuegoService = videojuegoService;
		// Inicializamos algunos datos
		initSampleData();
	}
	
	private void initSampleData() {
		// Crear objetos
		Inventario inventario = new Inventario(null,null,null,null);
		
		// Guardar los puestos en el inventario
		List<Puesto> listaDePuestos = puestoService.findAll();
		for (int i = 0; i < listaDePuestos.size() ; i++) {
			Map<String, Puesto> puesto = new HashMap<>();
			puesto.put(listaDePuestos.get(i).getId(), listaDePuestos.get(i));
		}
		inventario.setPuestos(puesto);
		
		// Guardar las consolas en el inventario
		List<Consola> listaDeConsolas = consolaService.findAll();
		for (int i = 0; i < listaDeConsolas.size() ; i++) {
			Map<String, Consola> consola = new HashMap<>();
			consola.put(listaDeConsolas.get(i).getId(), listaDeConsolas.get(i));
		}
		inventario.setConsolas(consola);
		// Guardar los objetos en el inventario
		List<Objeto> listaDeObjetos = objetoService.findAll();
		for (int i = 0; i < listaDeObjetos.size() ; i++) {
			Map<String, Objeto> objeto = new HashMap<>();
			objeto.put(listaDeObjetos.get(i).getId(), listaDeObjetos.get(i));
		}
		inventario.setObjetos(objeto);
		// Guardar los videojuegos en el inventario
		List<VideoJuego> listaDeVideojuegos = videojuegoService.findAll();
		for (int i = 0; i < listaDeVideojuegos.size() ; i++) {
			Map<String, VideoJuego> videojuego = new HashMap<>();
			videojuego.put(listaDeVideojuegos.get(i).getId(), listaDeVideojuegos.get(i));
		}
		inventario.setVideojuegos(videojuego);
		// Guardar el inventario en la base de datos
		save(inventario);
		
		
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
