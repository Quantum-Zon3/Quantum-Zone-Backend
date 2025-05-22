package com.example.Quantum_Zone_Backend.repository;
import java.util.Optional;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.Quantum_Zone_Backend.modelo.Objeto;
import java.time.LocalDate;
@Repository
public class ObjetoRepository {
	@PersistenceContext
	private EntityManager entityManager;
	//Guardar objeto
	@Transactional
	public Objeto save(Objeto objeto) {
		entityManager.persist(objeto);
		return objeto;
	}
	//Buscar todos los objetos
	public List<Objeto> findAll() {
		Query query = entityManager.createNativeQuery("SELECT * FROM objetos ", Objeto.class);
		return query.getResultList();
	}
	//Buscar objeto por id
	public Optional<Objeto> findById(Integer id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM objetos WHERE id = :id", Objeto.class);
		query.setParameter("id", id);
		try {
			Objeto objeto = (Objeto) query.getSingleResult();
			return Optional.of(objeto);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	//Eliminar objeto por id
	@Transactional
	public boolean deleteById(Integer id) {
		Query query = entityManager.createNativeQuery("DELETE FROM objetos WHERE id = :id");
		query.setParameter("id", id);
		int delete = query.executeUpdate();
		return delete > 0;
	}
	//Actualizar objeto
	@Transactional
	public Optional<Objeto> update(Integer id, Objeto objeto) {
		Query query = entityManager.createNativeQuery("UPDATE objetos SET nombre = :nombre, descripcion = :descripcion, fecha = :fecha, estado = :estado, categoria = :categoria WHERE id = :id");
		query.setParameter("nombre", objeto.getNombre());
		query.setParameter("descripcion", objeto.getDescripcion());
		query.setParameter("fecha", objeto.getFecha());
		query.setParameter("estado", objeto.getEstado());
		query.setParameter("categoria", objeto.getCategoria());
		query.setParameter("id", objeto.getId());
		int update = query.executeUpdate();
		if (update > 0) {
			return findById(id);
		} else {
			return Optional.empty();
		}
	}
	//Buscar objeto por filtros
	public Optional<List<Objeto>> findByFilters(String nombre) {
	    Query query = entityManager.createNativeQuery("SELECT * FROM objetos WHERE nombre LIKE :nombre", Objeto.class);
	    query.setParameter("nombre",nombre);
	    try {
	        List<Objeto> objetos = query.getResultList();
	        return Optional.of(objetos);
	    } catch (Exception e) {
	        return Optional.empty();
	    }
	}

}
