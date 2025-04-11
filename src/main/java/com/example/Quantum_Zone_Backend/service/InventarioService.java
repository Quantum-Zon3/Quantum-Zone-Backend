package com.example.Quantum_Zone_Backend.service;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.*;
import com.example.Quantum_Zone_Backend.repository.InventarioRepository;
@Service
public class InventarioService {

	private final InventarioRepository inventarioRepository;
	private final Inventario inventario;

	public InventarioService(InventarioRepository inventarioRepository) {
		this.inventarioRepository = inventarioRepository;
		List<Consola> consolas = new ArrayList<>();
		List<VideoJuego> juegos = new ArrayList<>();
		List<Objeto> objetos = new ArrayList<>();
		List<Puesto> puestos = new ArrayList<>();
		this.inventario = new Inventario(consolas, juegos, objetos, puestos);
		save(inventario);

		initSampleData();
	}

	private void initSampleData() {
		// Aquí puedes agregar datos de ejemplo si lo deseas
	}

	public List<Consola> saveConsola(Consola consola) {
		this.inventario.getConsolas().add(consola);
		return this.inventario.getConsolas();
	}

	public List<VideoJuego> saveVideoJuego(VideoJuego videojuego) {
		this.inventario.getJuegos().add(videojuego);
		return this.inventario.getJuegos();
	}

	public List<Objeto> saveObjeto(Objeto objeto) {
		this.inventario.getObjetos().add(objeto);
		return this.inventario.getObjetos();
	}

	public List<Puesto> savePuesto(Puesto puesto) {
		this.inventario.getPuestos().add(puesto);
		return this.inventario.getPuestos();
	}

	public void deleteConsola(String id) {
		this.inventario.getConsolas().removeIf(c -> c.getId().equals(id));
	}

	public void deleteVideoJuego(String id) {
		this.inventario.getJuegos().removeIf(v -> v.getId().equals(id));
	}

	public void deleteObjeto(String id) {
		this.inventario.getObjetos().removeIf(o -> o.getId().equals(id));
	}

	public void deletePuesto(String id) {
		this.inventario.getPuestos().removeIf(p -> p.getId().equals(id));
	}

	public Consola updateConsola(Consola consola) {
		deleteConsola(consola.getId());
		this.inventario.getConsolas().add(consola);
		return consola;
	}

	public VideoJuego updateVideoJuego(VideoJuego videojuego) {
		deleteVideoJuego(videojuego.getId());
		this.inventario.getJuegos().add(videojuego);
		return videojuego;
	}

	public Objeto updateObjeto(Objeto objeto) {
		deleteObjeto(objeto.getId());
		this.inventario.getObjetos().add(objeto);
		return objeto;
	}

	public Puesto updatePuesto(Puesto puesto) {
		deletePuesto(puesto.getId());
		this.inventario.getPuestos().add(puesto);
		return puesto;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public List<Consola> getConsolas() {
		return inventario.getConsolas();
	}

	public List<VideoJuego> getVideoJuegos() {
		return inventario.getJuegos();
	}

	public List<Objeto> getObjetos() {
		return inventario.getObjetos();
	}

	public List<Puesto> getPuestos() {
		return inventario.getPuestos();
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

	public List<Inventario> findByFilters(List<Consola> consolas,
            List<VideoJuego> videoJuegos,
            List<Objeto> objetos,
            List<Puesto> puestos) {
return inventarioRepository.findByFilters(consolas, videoJuegos, objetos, puestos);
}

}
