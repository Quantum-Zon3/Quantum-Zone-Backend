package com.example.Quantum_Zone_Backend.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "inventario")
public class Inventario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private List<Consola> consolas;
	@Column(nullable = false)
	private List<VideoJuego> juegos;
	@Column(nullable = false)
	private List<Objeto> objetos;
	@Column(nullable = false)
	private List<Puesto> puestos;
	
	public Inventario() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Consola> getConsolas() {
		return consolas;
	}

	public void setConsolas(List<Consola> consolas) {
		this.consolas = consolas;
	}

	public List< VideoJuego> getJuegos() {
		return juegos;
	}

	public void setJuegos(List< VideoJuego> juegos) {
		this.juegos = juegos;
	}

	public List< Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List< Objeto> objetos) {
		this.objetos = objetos;
	}

	public List< Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(List< Puesto> puestos) {
		this.puestos = puestos;
	}
	
}
