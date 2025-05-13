/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Quantum_Zone_Backend.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author shadow111285
 */
@Entity
@Table(name = "videojuegos")
public class VideoJuego {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private LocalDate fechaDePubliacion = LocalDate.now();
	@Column(nullable = false)
	private String descripcion;
	@Column(nullable = false)
	private String publico;
	@Column(nullable = false)
	private String tipo;

	public VideoJuego() {
		
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", fechaDePubliacion=" + fechaDePubliacion
				+ ", descripcion=" + descripcion + ", publico=" + publico + ", tipo=" + tipo + "]";
	}

}
