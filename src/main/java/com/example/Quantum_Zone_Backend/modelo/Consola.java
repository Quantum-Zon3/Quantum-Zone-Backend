/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author shadow111285
 */
public class Consola {
    private String marca;
    private String consola;
    private String fechaDePublicacion;

    public Consola(String marca, String consola, String fechaDePublicacion) {
        this.marca = marca;
        this.consola = consola;
        this.fechaDePublicacion = fechaDePublicacion;
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

    public String getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(String fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }
    
}
