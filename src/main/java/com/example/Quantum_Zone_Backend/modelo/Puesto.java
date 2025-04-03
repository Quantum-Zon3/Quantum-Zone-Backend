/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Quantum_Zone_Backend.modelo;
import java.util.UUID;
/**
 *
 * @author shadow111285
 */
public class Puesto {
	private String id;
    private String numeroDePuesto;
    private String consola;
    private int cantidadDeSillas;
    private int canditadDeControles;
    

    public Puesto(String numeroDePuesto, String consola, int cantidadDeSillas, int cantidadDeControles) {
    	this.id = UUID.randomUUID().toString();
        this.numeroDePuesto = numeroDePuesto;
        this.consola = consola;
        this.cantidadDeSillas = cantidadDeSillas;
        this.canditadDeControles = cantidadDeControles;
    }

    public String getNumeroDePuesto() {
        return numeroDePuesto;
    }

    public void setNumeroDePuesto(String numeroDePuesto) {
        this.numeroDePuesto = numeroDePuesto;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public int getCantidadDeSillas() {
        return cantidadDeSillas;
    }

    public void setCantidadDeSillas(int cantidadDeSillas) {
        this.cantidadDeSillas = cantidadDeSillas;
    }

    public int getCanditadDeControles() {
        return canditadDeControles;
    }

    public void setCanditadDeControles(int canditadDeControles) {
        this.canditadDeControles = canditadDeControles;
    }
    public String getId() {
    		return id;
    }

	@Override
	public String toString() {
		return "Puesto [id=" + id + ", numeroDePuesto=" + numeroDePuesto + ", consola=" + consola
				+ ", cantidadDeSillas=" + cantidadDeSillas + ", canditadDeControles=" + canditadDeControles + "]";
	}
    
    
}
