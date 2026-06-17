package riccardogulin.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document {
	@Id
	@GeneratedValue // Se non metto nessuna strategy, la modalità sarà AUTO, perfetta per gli UUID (me ne creerà uno unico ad ogni inserimento)
	@Column(name = "document_id")
	private UUID id;

	@Column(name = "issue_date", nullable = false)
	private LocalDate issueDate;
	@Column(name = "expiration_date", nullable = false)
	private LocalDate expirationDate;
	@Column(nullable = false)
	private String code;
	@Column(nullable = false)
	private String country;

	// 1 TO 1. La FK sta da questo lato
	@OneToOne // Annotazione OBBLIGATORIA se uso un attributo che ha un tipo corrispondente ad una Entity!
	@JoinColumn(name = "user_id", nullable = false, unique = true) // Annotazione OPZIONALE, però estremamente
	// utile per personalizzare la colonna FK
	private User owner; // Automaticamente creerà una colonna FK di tipo UUID

	public Document() {
	}

	public Document(String code, String country, User owner) {
		this.code = code;
		this.country = country;
		this.owner = owner;
		this.issueDate = LocalDate.now();
		this.expirationDate = LocalDate.now().plusYears(10);
	}

	public UUID getId() {
		return id;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public String getCode() {
		return code;
	}

	public String getCountry() {
		return country;
	}

	public User getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "Document{" +
				"id=" + id +
				", issueDate=" + issueDate +
				", expirationDate=" + expirationDate +
				", code='" + code + '\'' +
				", country='" + country + '\'' +
				", owner=" + owner +
				'}';
	}
}
