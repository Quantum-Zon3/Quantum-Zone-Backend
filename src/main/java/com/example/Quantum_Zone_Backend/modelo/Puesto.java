/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author shadow111285
 */
public class Puesto {
    private String numeroDePuesto;
    private String consola;
    private int cantidadDeSillas;
    private int canditadDeControles;
    

    public Puesto(String numeroDePuesto, String consola, int cantidadDeSillas, int cantidadDeControles) {
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
    
}
