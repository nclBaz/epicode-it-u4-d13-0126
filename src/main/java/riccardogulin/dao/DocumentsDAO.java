package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Document;

public class DocumentsDAO {
	// DAO = Data Access Object
	// E' un'astrazione, cioè, siccome le interazioni con il DB richiedono un po' di righe
	// di codice non particolarmente semplice, creiamo questa classe che fornirà dei metodi
	// semplici da usare nel main nascondendo queste complessità

	private final EntityManager entityManager;

	public DocumentsDAO(EntityManager em) {
		this.entityManager = em;
	}

	public void save(Document newDocument) {
		// TODO: Controlli di validazione
		EntityTransaction transaction = this.entityManager.getTransaction();

		transaction.begin();

		this.entityManager.persist(newDocument);

		transaction.commit();

		System.out.println("Il documento" + newDocument + " è stato salvato nel DB!");

	}

//	public User findById(long id) {
//		User fromDB = this.entityManager.find(User.class, id); // Se non trova niente mi torna NULL
//		if (fromDB == null) throw new NotFoundException(id);
//		return fromDB;
//	}
//


}
