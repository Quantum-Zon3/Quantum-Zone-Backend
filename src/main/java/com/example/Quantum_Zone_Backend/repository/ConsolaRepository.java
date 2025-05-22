package com.example.Quantum_Zone_Backend.repository;
import java.util.Optional;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.Quantum_Zone_Backend.modelo.Consola;
import java.time.LocalDate;

@Repository
public class ConsolaRepository {
	 @PersistenceContext
	 private EntityManager entityManager;
	 //Guardar consola
	 @Transactional
	 public Consola save(Consola consola) {
	 		 entityManager.persist(consola);
	        return consola;
	    }
	 //Buscar consola
	 @Transactional
	 public List<Consola> findAll() { 
		 Query query = entityManager.createNativeQuery("SELECT * FROM consolas ", Consola.class);
		 return query.getResultList();
	 }
	 //Buscar consola por id
	 @Transactional
	 public Optional<Consola> findById(Integer id) {
		 Query query = entityManager.createNativeQuery("SELECT * FROM consolas WHERE id = :id", Consola.class);
		 query.setParameter("id", id);
		 try {
			 Consola consola = (Consola) query.getSingleResult();
			 return Optional.of(consola);
		 } catch (Exception e) {
			 return Optional.empty();
		 }
	    }
	 //Eliminar consola por id
	 @Transactional
	 public boolean deleteById(Integer id) {
		 Query query = entityManager.createNativeQuery("DELETE FROM consolas WHERE id = :id");
		 query.setParameter("id", id);
		 int delete = query.executeUpdate();
		 return delete > 0;
	 }
	 //Actualizar consola
	 @Transactional
	 public Optional<Consola> update(Integer id,Consola consola) {
	    Query query = entityManager.createNativeQuery("UPDATE consolas SET marca = :marca, consola = :consola, fechaDePublicacion = :fechaDePublicacion WHERE id = :id");
	    query.setParameter("marca", consola.getMarca());
	    query.setParameter("consola", consola.getConsola());
	    query.setParameter("fechaDePublicacion", consola.getFechaDePublicacion());
	    query.setParameter("id", id);
	    int update = query.executeUpdate();
	    if (update > 0) {
	        return findById(id);
	    } else {
	        return Optional.empty();
	        }
	    }
	 //Buscar consola por filtros
	 public Optional<List<Consola>> findByFilters(String consola) {
	    Query query = entityManager.createNativeQuery("SELECT * FROM consolas WHERE consola LIKE :consola", Consola.class);
	    query.setParameter("consola", consola);
	    try {
	        List<Consola> consolas = query.getResultList();
	        return Optional.of(consolas);
	    } catch (Exception e) {
	        return Optional.empty();
	    }
	   }
}
