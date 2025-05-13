package com.example.Quantum_Zone_Backend.repository;
import java.util.Optional;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.Quantum_Zone_Backend.modelo.Puesto;
import com.example.Quantum_Zone_Backend.modelo.Consola;
@Repository
public class PuestoRepository {
	@PersistenceContext
	private EntityManager entityManager;
	//Guardar puesto
	@Transactional
	public Puesto save(Puesto puesto) {
		entityManager.persist(puesto);
		return puesto;
	}
	//Buscar todos los puestos
	@Transactional
	public List<Puesto> findAll() {
		Query query = entityManager.createNativeQuery("SELECT * FROM Puesto ", Puesto.class);
		return query.getResultList();
	}
	//Buscar puesto por id
	@Transactional
	public Optional<Puesto> findById(Integer id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Puesto WHERE id = :id", Puesto.class);
		query.setParameter("id", id);
		try {
			Puesto puesto = (Puesto) query.getSingleResult();
			return Optional.of(puesto);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	//Eliminar puesto por id
	@Transactional
	public boolean deleteById(Integer id) {
		Query query = entityManager.createNativeQuery("DELETE FROM Puesto WHERE id = :id");
		query.setParameter("id", id);
		int delete = query.executeUpdate();
		return delete > 0;
	}
	//Actualizar puesto
	@Transactional
	public Optional<Puesto> update(Integer id, Puesto puesto) {
		Query query = entityManager.createNativeQuery("UPDATE Puesto SET numeroDePuesto = :numeroDePuesto, idConsola = :idConsola, cantidadDeSillas = :cantidadDeSillas, canditadDeControles = :canditadDeControles WHERE id = :id");
		query.setParameter("numeroDePuesto", puesto.getNumeroDePuesto());
		query.setParameter("idConsola", puesto.getIdConsola());
		query.setParameter("cantidadDeSillas", puesto.getCantidadDeSillas());
		query.setParameter("canditadDeControles", puesto.getCanditadDeControles());
		query.setParameter("id", puesto.getId());
		int update = query.executeUpdate();
		if (update > 0) {
			return findById(puesto.getId());
		} else {
			return Optional.empty();
		}
	}
	//Buscar puesto por filtros
	public List<Puesto> findByFilters(String numeroDePuesto) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Puesto WHERE numeroDePuesto LIKE :numeroDePuesto", Puesto.class);
		query.setParameter("numeroDePuesto", numeroDePuesto);
		return query.getResultList();
	}
}
