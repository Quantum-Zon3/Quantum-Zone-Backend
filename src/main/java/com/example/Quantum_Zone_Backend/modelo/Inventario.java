package com.example.Quantum_Zone_Backend.modelo;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Inventario {
	private String id;
	private List< Consola> consolas;
	private List< VideoJuego> juegos;
	private List< Objeto> objetos;
	private List< Puesto> puestos;
	
	public Inventario(List< Consola> consolas, List< VideoJuego> juegos, List< Objeto> objetos,
			List< Puesto> puestos) {
		this.id = UUID.randomUUID().toString();
		this.consolas = consolas;
		this.juegos = juegos;
		this.objetos = objetos;
		this.puestos = puestos;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public List<Consola> getConsolas() {
		return consolas;
	}

	public void setConsolas(List<Consola> consolas) {
		this.consolas = consolas;
	}

	public List< VideoJuego> getJuegos() {
		return juegos;
	}

	public void setJuegos(List< VideoJuego> juegos) {
		this.juegos = juegos;
	}

	public List< Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List< Objeto> objetos) {
		this.objetos = objetos;
	}

	public List< Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(List< Puesto> puestos) {
		this.puestos = puestos;
	}
	
}
