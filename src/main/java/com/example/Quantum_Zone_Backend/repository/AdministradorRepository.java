package com.example.Quantum_Zone_Backend.repository;
import java.util.Optional;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.example.Quantum_Zone_Backend.modelo.Administrador;

@Repository
public class AdministradorRepository {
	@PersistenceContext
	private EntityManager entityManager;
	//Guardar Administrador
	@Transactional
	public Administrador save(Administrador administrador) {
		entityManager.persist(administrador);
		return administrador;
	}
	//Buscar todos los Administradores
	@Transactional
	public List<Administrador> findAll() {
		Query query = entityManager.createNativeQuery("SELECT * FROM Administrador ", Administrador.class);
		return query.getResultList();
	}
	//Buscar administrador por id
	@Transactional
	public Optional <Administrador> findById(Integer id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Administrador WHERE id = :id", Administrador.class);
		query.setParameter("id", id);
		try {
			Administrador administrador = (Administrador) query.getSingleResult();
			return Optional.of(administrador);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	//Eliminar administrador por id
	@Transactional
	public boolean deleteById(Integer id) {
		Query query = entityManager.createNativeQuery("DELETE FROM Administrador WHERE id = :id");
		query.setParameter("id", id);
		int delete = query.executeUpdate();
		return delete > 0;
	}
	//Actualizar administrador
	@Transactional
	public Optional<Administrador> update(Integer id,Administrador administrador) {
		Query query = entityManager.createNativeQuery("UPDATE Administrador SET nombre = :nombre, contraseña = :contraseña, edad = :edad, cedula = :cedula WHERE id = :id");
		query.setParameter("nombre", administrador.getNombre());
		query.setParameter("contraseña", administrador.getContraseña());
		query.setParameter("edad", administrador.getEdad());
		query.setParameter("cedula", administrador.getCedula());
		query.setParameter("id", id);
		int update = query.executeUpdate();
		if (update > 0) {
			return findById(id);
		} else {
			return Optional.empty();
		}
	}
	//Buscar administrador por filtros
	public List<Administrador> findByFilters(String nombre) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Administrador WHERE nombre LIKE :nombre", Administrador.class);
		query.setParameter("nombre",  nombre );
		return query.getResultList();
	}
}
