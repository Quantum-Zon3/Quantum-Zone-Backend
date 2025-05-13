package com.example.Quantum_Zone_Backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Repository
public class VideoJuegoRepository {
	private boolean initialized = false;
    @PostConstruct
    public void init() {
        if (!initialized) {
            System.out.println("Inicializando repositorio de categorias");

            initialized = true;
        }
    }
    @PersistenceContext
    private EntityManager entityManager;
	@Transactional
	public VideoJuego save(VideoJuego videojuego) {
        entityManager.persist(videojuego);
        return videojuego;
    }
	@Transactional
	public List<VideoJuego> findAll() {
	    Query query = entityManager.createNativeQuery("SELECT * FROM VideoJuego", VideoJuego.class);
	    return query.getResultList();
	}
	@Transactional
	public Optional<VideoJuego> findById(String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM VideoJuego WHERE id = :id", VideoJuego.class);
        query.setParameter("id", id);
        try {
			VideoJuego videojuego = (VideoJuego) query.getSingleResult();
			return Optional.of(videojuego);
		} catch (Exception e) {
			return Optional.empty();
		}
    }
	@Transactional
	public boolean deleteById(String id) {
		Query query = entityManager.createNativeQuery("DELETE FROM VideoJuego WHERE id = :id");
		query.setParameter("id", id);
		int delete = query.executeUpdate();
		return delete > 0;
	}
	//Actualizar videojuego
	@Transactional
	public Optional<VideoJuego> update(String id,VideoJuego videojuego) {
        Query query = entityManager.createNativeQuery("UPDATE VideoJuego SET nombre = :nombre, fechaDePublicacion = :fechaDePublicacion, descripcion = :descripcion, publico = :publico, tipo = :tipo WHERE id = :id");
        query.setParameter("nombre", videojuego.getNombre());
        query.setParameter("fechaDePublicacion", videojuego.getFechaDePubliacion());
        query.setParameter("descripcion", videojuego.getDescripcion());
        query.setParameter("publico", videojuego.getPublico());
        query.setParameter("tipo", videojuego.getTipo());
        query.setParameter("id", id);
        int update = query.executeUpdate();
        if (update > 0) {
			return findById(id);
		} else {
			return Optional.empty();
		}
    }
	public List<VideoJuego> findByFilters(String nombre) {
		Query query = entityManager.createNativeQuery("SELECT * FROM VideoJuego WHERE nombre LIKE :nombre", VideoJuego.class);
		query.setParameter("nombre",  nombre );
		return query.getResultList();
	}
}
