/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author shadow111285
 */
public class Videojuego {
    private String nombre;
    private String fechaDePubliacion;
    private String descripcion;
    private String publico;
    private String tipo;

    public Videojuego(String nombre, String fechaDePubliacion, String descripcion, String publico, String tipo) {
        this.nombre = nombre;
        this.fechaDePubliacion = fechaDePubliacion;
        this.descripcion = descripcion;
        this.publico = publico;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDePubliacion() {
        return fechaDePubliacion;
    }

    public void setFechaDePubliacion(String fechaDePubliacion) {
        this.fechaDePubliacion = fechaDePubliacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
