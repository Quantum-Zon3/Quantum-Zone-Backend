package com.example.Quantum_Zone_Backend.modelo;
import java.time.LocalDate;

import com.example.Quantum_Zone_Backend.modelo.*;

public class VideojuegoRentado {
        private String id;
	private Cliente cliente;
	private VideoJuego videojuego;
	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	
	public VideojuegoRentado(Cliente cliente, VideoJuego videojuego, LocalDate fechaAlquiler, LocalDate fechaDevolucion) {
		this.cliente = cliente;
		this.videojuego = videojuego;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaDevolucion = fechaDevolucion;
	}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VideoJuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(VideoJuego videojuego) {
        this.videojuego = videojuego;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VideojuegoRentado{" + "id=" + id + ", cliente=" + cliente + ", videojuego=" + videojuego + ", fechaAlquiler=" + fechaAlquiler + ", fechaDevolucion=" + fechaDevolucion + '}';
    }
    
}
