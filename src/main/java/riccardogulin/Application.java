package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.BlogsDAO;
import riccardogulin.dao.CategoriesDAO;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.Blog;
import riccardogulin.entities.Category;
import riccardogulin.entities.Document;
import riccardogulin.entities.User;
import riccardogulin.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Application {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4d13pu");

	public static void main(String[] args) {
		EntityManager em = entityManagerFactory.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);
		BlogsDAO bd = new BlogsDAO(em);
		CategoriesDAO cd = new CategoriesDAO(em);

		User aldo = new User("Aldo", "Baglio"); // NON MANAGED ~ TRANSIENT

		// ******************************** 1 TO 1 ***************************************
		// Document aldoDoc = new Document("1234", "Italy", aldo);
		// Non posso usare aldo perchè è un oggetto new (cioè non managed o transient), è un oggetto semplice che nulla ha a che fare con il DB
		// Per ottenere oggetti MANAGED ho 2 opzioni: o lo salvo (persist) oppure lo LEGGO DAL DB

		try {
			User aldoFromDB = ud.findById("173786ca-3dcf-4b6f-99f3-4e7db57f9dad");

//			System.out.println(aldoFromDB.getDocument());

			// Document aldoDoc = new Document("1234", "Italy", aldoFromDB); // aldoFromDB è MANAGED
//			dd.save(aldoDoc);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		Document aldoDocFromDB = dd.findById("1445fb47-81e2-4232-ad51-b695efb52d48");
//		System.out.println(aldoDocFromDB.getOwner());


		// ******************************** 1 TO N ***************************************
		try {
			User aldoFromDB = ud.findById("173786ca-3dcf-4b6f-99f3-4e7db57f9dad");

			aldoFromDB.getBlogs().forEach(System.out::println);

//
//			Blog javaBlog = new Blog("Postgres", "E' bellissimo!", aldoFromDB);
//
//			// bd.save(javaBlog);
//
//			Blog javaFromDB = bd.findById("154694af-4d4c-4cef-8f97-8474a7befb6a");
//			System.out.println(javaFromDB.getAuthor());


		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


		// ******************************** N TO N ***************************************
		Category cat1 = new Category("Backend");
		Category cat2 = new Category("Frontend");
		Category cat3 = new Category("OOP");
		Category cat4 = new Category("Java");
		Category cat5 = new Category("Databases");


		try {

			// 1. Leggo il Blog da DB
			Blog javaFromDB = bd.findById("154694af-4d4c-4cef-8f97-8474a7befb6a");
			// 2. Cerco le categorie che mi interessano nel DB
			Category backendCatFromDB = cd.findById("b9a7783f-43ce-4a1d-92bb-2ee6c10aa277");
			Category oopCatFromDB = cd.findById("5f3d2f9c-3d38-4a2f-849c-454b1a1250aa");
			Category javaCatFromDB = cd.findById("1db2ea82-46be-4c2f-a80e-36e0a2ca8519");

			// 3. Creo una lista con le 3 categorie MANAGED
			ArrayList<Category> categories = new ArrayList<>(List.of(backendCatFromDB, oopCatFromDB, javaCatFromDB));

			// 4. Passo la lista al Blog tramite setter
			javaFromDB.setCategories(categories);

			// 5. Ri-salvo il blog così modificato in modo da assegnargli le categorie
			// bd.save(javaFromDB); // Dietro le quinte JPA si occuperà di prendere gli id delle 3 categorie e li inserirà nella JUNCTION TABLE
			// collegandoli all'id del blog

			System.out.println("Le categorie del blog Java sono:");
			javaFromDB.getCategories().forEach(System.out::println);

			System.out.println("I blog della categoria Backend sono: ");
			backendCatFromDB.getBlogs().forEach(System.out::println);


		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

	}
}
