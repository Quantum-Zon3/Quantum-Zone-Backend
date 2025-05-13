package com.example.Quantum_Zone_Backend.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import com.example.Quantum_Zone_Backend.modelo.*;
import java.util.UUID;

@Entity
@Table(name = "videojuegosRentados")
public class VideojuegoRentado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false)
	private Integer idCliente;
	@Column(nullable = false)
	private Integer idVideojuego;
	@Column(nullable = false)
	private LocalDate fechaAlquiler = LocalDate.now();
	@Column(nullable = false)
	private LocalDate fechaDevolucion;
	
	public VideojuegoRentado() {
	}

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdVideojuego() {
        return this.idVideojuego;
    }

    public void setIdVideojuego(Integer idVideojuego) {
        this.idVideojuego = idVideojuego;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VideojuegoRentado{" + "id=" + id + ", cliente=" + idCliente + ", videojuego=" + idVideojuego + ", fechaAlquiler=" + fechaAlquiler + ", fechaDevolucion=" + fechaDevolucion + '}';
    }
    
}
