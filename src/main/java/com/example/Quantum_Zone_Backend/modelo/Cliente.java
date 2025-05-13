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
import java.util.UUID;
import java.time.LocalDate;
/**
 *
 * @author shadow111285
 */
@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false)
    private String nombre;
	@Column(nullable = false)
    private int edad;
	@Column(nullable = false)
    private String direccion;
	@Column(nullable = false)
    private String imagen;
	@Column(nullable = false)
    private String cedula;
	@Column(nullable = false)
    private String telefono;
	@Column(nullable = false)
    private LocalDate fechaRegistro;
	@Column(nullable = false)
    private String email;

    
    public Cliente() {
		
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getId() {
    	return id;
    }
    public void setId(Integer id) {
		this.id = id;
	}

    @Override
    public String toString() {
		return "Cliente{" +
				"id='" + id + '\'' +
				", nombre='" + nombre + '\'' +
				", edad=" + edad +
				", direccion='" + direccion + '\'' +
				", imagen='" + imagen + '\'' +
				", cedula='" + cedula + '\'' +
				", telefono='" + telefono + '\'' +
				", fechaRegistro='" + fechaRegistro + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
