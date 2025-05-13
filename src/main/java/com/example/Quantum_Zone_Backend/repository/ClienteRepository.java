package com.example.Quantum_Zone_Backend.repository;
import java.util.Optional;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.Quantum_Zone_Backend.modelo.Cliente;
import java.time.LocalDate;
@Repository
public class ClienteRepository {
	@PersistenceContext
	private EntityManager entityManager;
	//Guardar cliente
	@Transactional
	public Cliente save(Cliente cliente) {
		entityManager.persist(cliente);
		return cliente;
	}
	//Buscar todos los clientes
	@Transactional
	public List<Cliente> findAll() {
		Query query = entityManager.createNativeQuery("SELECT * FROM Cliente ", Cliente.class);
		return query.getResultList();
	}
	//Buscar cliente por id
	@Transactional
	public Optional<Cliente> findById(String id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Cliente WHERE id = :id", Cliente.class);
		query.setParameter("id", id);
		try {
			Cliente cliente = (Cliente) query.getSingleResult();
			return Optional.of(cliente);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	//Eliminar cliente por id
	public boolean deleteById(String id) {
		Query query = entityManager.createNativeQuery("DELETE FROM Cliente WHERE id = :id");
		query.setParameter("id", id);
		int delete = query.executeUpdate();
		return delete > 0;
	}
	//Actualizar cliente
	public Optional<Cliente>update(String id,Cliente cliente) {
		Query query = entityManager.createNativeQuery("UPDATE Cliente SET nombre = :nombre, edad = :edad, direccion = :direccion, imagen = :imagen, cedula = :cedula, telefono = :telefono, fechaRegistro = :fechaRegistro, email = :email WHERE id = :id");
		query.setParameter("nombre", cliente.getNombre());
		query.setParameter("edad", cliente.getEdad());
		query.setParameter("direccion", cliente.getDireccion());
		query.setParameter("imagen", cliente.getImagen());
		query.setParameter("cedula", cliente.getCedula());
		query.setParameter("telefono", cliente.getTelefono());
		query.setParameter("fechaRegistro", cliente.getFechaRegistro());
		query.setParameter("email", cliente.getEmail());
		query.setParameter("id", cliente.getId());
		int update = query.executeUpdate();
		if (update > 0) {
			return findById(id);
		} else {
			return Optional.empty();
		}
	}
	//Buscar cliente por filtros
	public List<Cliente> findByFilters(String nombre) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Cliente WHERE nombre LIKE :nombre", Cliente.class);
		query.setParameter("nombre", nombre );
		return query.getResultList();
	}
}
