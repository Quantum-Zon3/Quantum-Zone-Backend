package com.example.Quantum_Zone_Backend.modelo;

import java.util.Map;
import java.util.UUID;

public class Inventario {
	private String id;
	private Map<String, Consola> consolas;
	private Map<String, Videojuego> juegos;
	private Map<String, Objeto> objetos;
	private Map<String, Puesto> puestos;
	
	public Inventario(Map<String, Consola> consolas, Map<String, Videojuego> juegos, Map<String, Objeto> objetos,
			Map<String, Puesto> puestos) {
		this.id = UUID.randomUUID().toString();
		this.consolas = consolas;
		this.juegos = juegos;
		this.objetos = objetos;
		this.puestos = puestos;
	}

	public String getId() {
		return id;
	}

	public Map<String, Consola> getConsolas() {
		return consolas;
	}

	public void setConsolas(Map<String, Consola> consolas) {
		this.consolas = consolas;
	}

	public Map<String, Videojuego> getJuegos() {
		return juegos;
	}

	public void setJuegos(Map<String, Videojuego> juegos) {
		this.juegos = juegos;
	}

	public Map<String, Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(Map<String, Objeto> objetos) {
		this.objetos = objetos;
	}

	public Map<String, Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(Map<String, Puesto> puestos) {
		this.puestos = puestos;
	}
	
}
