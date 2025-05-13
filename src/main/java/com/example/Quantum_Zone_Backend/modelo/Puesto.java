package com.example.Quantum_Zone_Backend.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
/**
 *
 * @author shadow111285
 */
@Entity
@Table(name = "puestos")
public class Puesto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
    private String numeroDePuesto;
	@Column(name = "idConsola" ,nullable = false)
    private Integer idConsola;
	@Column(nullable = false)
    private int cantidadDeSillas;
	@Column(nullable = false)
    private int canditadDeControles;
    

    public Puesto() {
    }

    public String getNumeroDePuesto() {
        return numeroDePuesto;
    }

    public void setNumeroDePuesto(String numeroDePuesto) {
        this.numeroDePuesto = numeroDePuesto;
    }

    public Integer getIdConsola() {
        return idConsola;
    }

    public void setIdConsola(Integer idConsola) {
        this.idConsola = idConsola;
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
    public Integer getId() {
    		return id;
    }
    public void setId(Integer id) {
			this.id = id;
	}

	@Override
	public String toString() {
		return "Puesto [id=" + id + ", numeroDePuesto=" + numeroDePuesto + ", consola=" + consola
				+ ", cantidadDeSillas=" + cantidadDeSillas + ", canditadDeControles=" + canditadDeControles + "]";
	}
    
    
}
