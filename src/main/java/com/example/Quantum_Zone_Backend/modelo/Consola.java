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
@Table(name = "consolas")
public class Consola {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false)
    private String marca;
	@Column(nullable = false)
    private String consola;
	@Column(nullable = false)
    private LocalDate fechaDePublicacion;


    public Consola() {
	}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public LocalDate getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(LocalDate fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }
    public Integer getId() {
		return id;
	}
    	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Consola [id=" + id + ", marca=" + marca + ", consola=" + consola + ", fechaDePublicacion="
				+ fechaDePublicacion + "]";
	}
    
    
}
