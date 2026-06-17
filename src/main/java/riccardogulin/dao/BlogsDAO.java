package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Blog;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class BlogsDAO {
	// DAO = Data Access Object
	// E' un'astrazione, cioè, siccome le interazioni con il DB richiedono un po' di righe
	// di codice non particolarmente semplice, creiamo questa classe che fornirà dei metodi
	// semplici da usare nel main nascondendo queste complessità

	private final EntityManager entityManager;

	public BlogsDAO(EntityManager em) {
		this.entityManager = em;
	}

	public void save(Blog newBlog) {
		// TODO: Controlli di validazione
		EntityTransaction transaction = this.entityManager.getTransaction();

		transaction.begin();

		this.entityManager.persist(newBlog);

		transaction.commit();

		System.out.println("Il blog " + newBlog + " è stato salvato nel DB!");

	}

	public Blog findById(String id) {
		Blog fromDB = this.entityManager.find(Blog.class, UUID.fromString(id)); // Se non trova niente mi torna NULL
		if (fromDB == null) throw new NotFoundException(id);
		return fromDB;
	}


}
