package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.Document;
import riccardogulin.entities.User;
import riccardogulin.exceptions.NotFoundException;

public class Application {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4d13pu");

	public static void main(String[] args) {
		EntityManager em = entityManagerFactory.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);

		User aldo = new User("Aldo", "Baglio"); // NON MANAGED ~ TRANSIENT

		// ******************************** 1 TO 1 ***************************************
		// Document aldoDoc = new Document("1234", "Italy", aldo);
		// Non posso usare aldo perchè è un oggetto new (cioè non managed o transient), è un oggetto semplice che nulla ha a che fare con il DB
		// Per ottenere oggetti MANAGED ho 2 opzioni: o lo salvo (persist) oppure lo LEGGO DAL DB

		try {
			User aldoFromDB = ud.findById("173786ca-3dcf-4b6f-99f3-4e7db57f9dad");
			Document aldoDoc = new Document("1234", "Italy", aldoFromDB); // aldoFromDB è MANAGED
			dd.save(aldoDoc);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


	}
}
