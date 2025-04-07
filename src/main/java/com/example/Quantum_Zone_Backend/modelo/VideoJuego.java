/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Quantum_Zone_Backend.modelo;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author shadow111285
 */
public class VideoJuego {
	private String id;
	private String nombre;
	private LocalDate fechaDePubliacion;
	private String descripcion;
	private String publico;
	private String tipo;

	public VideoJuego(String nombre, LocalDate fechaDePubliacion, String descripcion, String publico, String tipo) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.fechaDePubliacion = fechaDePubliacion;
		this.descripcion = descripcion;
		this.publico = publico;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaDePubliacion() {
		return fechaDePubliacion;
	}

	public void setFechaDePubliacion(LocalDate fechaDePubliacion) {
		this.fechaDePubliacion = fechaDePubliacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPublico() {
		return publico;
	}

	public void setPublico(String publico) {
		this.publico = publico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", fechaDePubliacion=" + fechaDePubliacion
				+ ", descripcion=" + descripcion + ", publico=" + publico + ", tipo=" + tipo + "]";
	}

}
