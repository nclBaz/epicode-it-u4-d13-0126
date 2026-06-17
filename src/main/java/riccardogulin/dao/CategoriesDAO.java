package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Category;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class CategoriesDAO {
	// DAO = Data Access Object
	// E' un'astrazione, cioè, siccome le interazioni con il DB richiedono un po' di righe
	// di codice non particolarmente semplice, creiamo questa classe che fornirà dei metodi
	// semplici da usare nel main nascondendo queste complessità

	private final EntityManager entityManager;

	public CategoriesDAO(EntityManager em) {
		this.entityManager = em;
	}

	public void save(Category newCategory) {
		// TODO: Controlli di validazione
		EntityTransaction transaction = this.entityManager.getTransaction();

		transaction.begin();

		this.entityManager.persist(newCategory);

		transaction.commit();

		System.out.println("La categoria" + newCategory + " è stata salvata nel DB!");

	}

	public Category findById(String id) {
		Category fromDB = this.entityManager.find(Category.class, UUID.fromString(id)); // Se non trova niente mi torna NULL
		if (fromDB == null) throw new NotFoundException(id);
		return fromDB;
	}


}
