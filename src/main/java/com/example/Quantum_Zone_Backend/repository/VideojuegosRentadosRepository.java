package com.example.Quantum_Zone_Backend.repository;
import java.util.Optional;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Cliente;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.modelo.VideojuegoRentado;
import java.time.LocalDate;
@Repository
public class VideojuegosRentadosRepository {
	@PersistenceContext
	private EntityManager entityManager;
	//Guardar videojuego
	@Transactional
	public VideojuegoRentado save(VideojuegoRentado videojuegoRentado) {
		entityManager.persist(videojuegoRentado);
		return videojuegoRentado;
	}
	public List<VideojuegoRentado> findAll() {
		Query query = entityManager.createNativeQuery("SELECT * FROM VideojuegoRentado", VideojuegoRentado.class);
		return query.getResultList();
	}
	public Optional<VideojuegoRentado> findById(String id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM VideojuegoRentado WHERE id = :id", VideojuegoRentado.class);
		query.setParameter("id", id);
		try {
			VideojuegoRentado videojuegoRentado = (VideojuegoRentado) query.getSingleResult();
			return Optional.of(videojuegoRentado);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	public boolean deleteById(String id) {
		Query query = entityManager.createNativeQuery("DELETE FROM VideojuegoRentado WHERE id = :id");
		query.setParameter("id", id);
		int delete = query.executeUpdate();
		return delete > 0;
	}
	public Optional<VideojuegoRentado> update(String id,VideojuegoRentado videojuegoRentado) {
		Query query = entityManager.createNativeQuery("UPDATE VideojuegoRentado SET cliente = :cliente, videojuego = :videojuego, fechaAlquiler = :fechaAlquiler, fechaDevolucion = :fechaDevolucion WHERE id = :id");
		query.setParameter("cliente", videojuegoRentado.getCliente());
		query.setParameter("videojuego", videojuegoRentado.getVideojuego());
		query.setParameter("fechaAlquiler", videojuegoRentado.getFechaAlquiler());
		query.setParameter("fechaDevolucion", videojuegoRentado.getFechaDevolucion());
		query.setParameter("id", id);
		int update = query.executeUpdate();
		if (update > 0) {
			return findById(id);
		} else {
			return Optional.empty();
		}
	}
	public List<VideojuegoRentado> findByFilters(VideoJuego videojuego) {
		Query query = entityManager.createNativeQuery("SELECT * FROM VideojuegoRentado WHERE videojuego = :videojuego", VideojuegoRentado.class);
		query.setParameter("videojuego", videojuego);
		return query.getResultList();
	}
}
