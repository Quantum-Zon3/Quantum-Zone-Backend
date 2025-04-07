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
	private final PuestoService puestoService;
	private final ConsolaService consolaService;
	private final ObjetoService objetoService;
	private final VideoJuegoService videojuegoService;
	private final Inventario inventario;
	
	public InventarioService(InventarioRepository inventarioRepository,PuestoService puestoService,ConsolaService consolaService,ObjetoService objetoService,VideoJuegoService videojuegoService) {
		this.inventarioRepository = inventarioRepository;
		this.puestoService = puestoService;
		this.consolaService = consolaService;
		this.objetoService = objetoService;
		this.videojuegoService = videojuegoService;
		this.inventario = new Inventario(null,null,null,null);
		
		// Inicializamos algunos datos
		initSampleData();
	}
	
	private void initSampleData() {
		// Crear objetos
		
		
		// Guardar los puestos en el inventario
		List<Puesto> listaDePuestos = puestoService.findAll();
		Map<String, Puesto> puesto = new HashMap<>();
		for (int i = 0; i < listaDePuestos.size() ; i++) {
			puesto.put(listaDePuestos.get(i).getId(), listaDePuestos.get(i));
		}
		this.inventario.setPuestos(puesto);
		
		// Guardar las consolas en el inventario
		List<Consola> listaDeConsolas = consolaService.findAll();
		Map<String, Consola> consola = new HashMap<>();
		for (int i = 0; i < listaDeConsolas.size() ; i++) {
			consola.put(listaDeConsolas.get(i).getId(), listaDeConsolas.get(i));
		}
		this.inventario.setConsolas(consola);
		// Guardar los objetos en el inventario
		List<Objeto> listaDeObjetos = objetoService.findAll();
		Map<String, Objeto> objeto = new HashMap<>();
		for (int i = 0; i < listaDeObjetos.size() ; i++) {
			objeto.put(listaDeObjetos.get(i).getId(), listaDeObjetos.get(i));
		}
		this.inventario.setObjetos(objeto);
		// Guardar los videojuegos en el inventario
		List<VideoJuego> listaDeVideojuegos = videojuegoService.findAll();
		Map<String, VideoJuego> videojuego = new HashMap<>();
		for (int i = 0; i < listaDeVideojuegos.size() ; i++) {
			videojuego.put(listaDeVideojuegos.get(i).getId(), listaDeVideojuegos.get(i));
		}
		this.inventario.setJuegos(videojuego);
		// Guardar el inventario en la base de datos
		save(inventario);
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
