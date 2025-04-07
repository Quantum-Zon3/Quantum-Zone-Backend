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
public class Consola {
	private String id;
    private String marca;
    private String consola;
    private LocalDate fechaDePublicacion;

    public Consola(String marca, String consola, LocalDate fechaDePublicacion) {
    	this.id = UUID.randomUUID().toString();
        this.marca = marca;
        this.consola = consola;
        this.fechaDePublicacion = fechaDePublicacion;
    }
    public Consola() {
		this.id = UUID.randomUUID().toString();
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
    public String getId() {
		return id;
	}
    	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Consola [id=" + id + ", marca=" + marca + ", consola=" + consola + ", fechaDePublicacion="
				+ fechaDePublicacion + "]";
	}
    
    
}
