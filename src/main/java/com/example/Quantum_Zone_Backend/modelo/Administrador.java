/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Quantum_Zone_Backend.modelo;
import java.util.UUID;
/**
 *
 */
public class Administrador {
	private String id;
    private String nombre;
    private String contraseña;
    private int edad;
    private String cedula;

    public Administrador(String nombre, String contraseña, int edad, String cedula) {
    	this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.edad = edad;
        this.cedula = cedula;
    }
    public Administrador() {
    	this.id = UUID.randomUUID().toString();;
		
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getId() {
		return id;
	}
    
   
	
	@Override
	public String toString() {
		return "Administrador{" +
				"nombre='" + nombre + '\'' +
				", contraseña='" + contraseña + '\'' +
				", edad=" + edad +
				", cedula='" + cedula + '\'' +
				'}';
	}
    
}
