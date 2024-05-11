package costa.costa.luiz.unit.movie.application.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "genres")
public class Genre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static final class GenreBuilder {
        private Long id;
        private String name;

        private GenreBuilder() {
        }

        public static GenreBuilder aGenre() {
            return new GenreBuilder();
        }

        public GenreBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public GenreBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Genre build() {
            Genre genre = new Genre();
            genre.setId(id);
            genre.setName(name);
            return genre;
        }
    }
}
