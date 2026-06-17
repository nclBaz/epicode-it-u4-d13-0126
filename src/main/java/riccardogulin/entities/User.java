package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue // Se non metto nessuna strategy, la modalità sarà AUTO, perfetta per gli UUID (me ne creerà uno unico ad ogni inserimento)
	@Column(name = "user_id")
	private UUID id;
	private String name;
	private String surname;

	// 1 TO 1 BIDIREZIONALE
	// La bidirezionalità NON E' OBBLIGATORIA! Lo faccio solo se mi serve un modo
	// per, una volta ottenuto un utente, risalire al suo documento
	@OneToOne(mappedBy = "owner") // owner è il nome dell'attributo lato Document
	private Document document;

	// 1 TO MANY BIDIREZIONALE
	@OneToMany(mappedBy = "author")
	private List<Blog> blogs;


	public User() {
	}

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public Document getDocument() {
		return document;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}
