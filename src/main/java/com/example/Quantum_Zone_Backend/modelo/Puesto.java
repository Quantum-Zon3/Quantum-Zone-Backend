

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
    private String cantidadDeSillas;
    private String canditadDeControles;
    

    public Puesto(String numeroDePuesto, String consola, String cantidadDeSillas, String cantidadDeControles) {
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

    public String getCantidadDeSillas() {
        return cantidadDeSillas;
    }

    public void setCantidadDeSillas(String cantidadDeSillas) {
        this.cantidadDeSillas = cantidadDeSillas;
    }

    public String getCanditadDeControles() {
        return canditadDeControles;
    }

    public void setCanditadDeControles(String canditadDeControles) {
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
