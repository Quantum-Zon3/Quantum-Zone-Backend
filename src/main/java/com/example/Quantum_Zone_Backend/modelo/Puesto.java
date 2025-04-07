

package com.example.Quantum_Zone_Backend.modelo;
import java.util.UUID;
/**
 *
 * @author shadow111285
 */
public class Puesto {
	private String id;
    private String numeroDePuesto;
    private Consola consola;
    private int cantidadDeSillas;
    private int canditadDeControles;
    

    public Puesto(String numeroDePuesto, Consola consola, int cantidadDeSillas, int cantidadDeControles) {
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

    public Consola getConsola() {
        return consola;
    }

    public void setConsola(Consola consola) {
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
    public void setId(String id) {
			this.id = id;
	}

	@Override
	public String toString() {
		return "Puesto [id=" + id + ", numeroDePuesto=" + numeroDePuesto + ", consola=" + consola
				+ ", cantidadDeSillas=" + cantidadDeSillas + ", canditadDeControles=" + canditadDeControles + "]";
	}
    
    
}
