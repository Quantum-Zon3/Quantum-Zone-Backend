/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Quantum_Zone_Backend.modelo;
import java.util.UUID;
import java.time.LocalDate;
/**
 *
 * @author shadow111285
 */
public class Cliente {
	private String id;
    private String nombre;
    private int edad;
    private String direccion;
    private String imagen;
    private String cedula;
    private String telefono;
    private LocalDate fechaRegistro;
    private String email;

    public Cliente(String nombre, int edad, String direccion, String imagen, String telefono, LocalDate fechaRegistro, String email) {
    	this.id = UUID.randomUUID().toString();
    	this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.imagen = imagen;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.email = email;
    }
    public Cliente() {
		this.id = UUID.randomUUID().toString();
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
    public String getId() {
    	return id;
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
