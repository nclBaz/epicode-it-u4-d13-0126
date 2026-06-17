package riccardogulin.entities;

import jakarta.persistence.*;

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

	public User() {
	}

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
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
