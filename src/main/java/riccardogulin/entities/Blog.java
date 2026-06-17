package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "blogs")
public class Blog {
	@Id
	@GeneratedValue // Se non metto nessuna strategy, la modalità sarà AUTO, perfetta per gli UUID (me ne creerà uno unico ad ogni inserimento)
	@Column(name = "blog_id")
	private UUID id;

	private String title;
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User author;

	@ManyToMany
	@JoinTable(name = "blogs_categories",
			joinColumns = @JoinColumn(name = "blog_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	// @JoinTable non è obbligatoria però è molto consigliata perché mi permette di personalizzare
	// le caratteristiche della JUNCTION TABLE
	private List<Category> categories;

	public Blog() {
	}

	public Blog(String title, String content, User author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return "Blog{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", author=" + author +
				", categories=" + categories +
				'}';
	}
}
