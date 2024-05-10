package costa.costa.luiz.unit.movie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

public class MovieDTO {

    private Long id;
    private String title;
    private int releasedYear;
    private int minutes;
    private String genres;
    private double rating;
    private String overview;
    private String director;
    private String actors;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getGenres() {
        return genres;
    }

    public double getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }


    public static final class MovieDTOBuilder {
        private Long id;
        private String title;
        private int releasedYear;
        private int minutes;
        private String genres;
        private double rating;
        private String overview;
        private String director;
        private String actors;

        private MovieDTOBuilder() {
        }

        public static MovieDTOBuilder aMovieDTO() {
            return new MovieDTOBuilder();
        }

        public MovieDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public MovieDTOBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieDTOBuilder withReleasedYear(int releasedYear) {
            this.releasedYear = releasedYear;
            return this;
        }

        public MovieDTOBuilder withMinutes(int minutes) {
            this.minutes = minutes;
            return this;
        }

        public MovieDTOBuilder withGenres(String genres) {
            this.genres = genres;
            return this;
        }

        public MovieDTOBuilder withRating(double rating) {
            this.rating = rating;
            return this;
        }

        public MovieDTOBuilder withOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public MovieDTOBuilder withDirector(String director) {
            this.director = director;
            return this;
        }

        public MovieDTOBuilder withActors(String actors) {
            this.actors = actors;
            return this;
        }

        public MovieDTO build() {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.releasedYear = this.releasedYear;
            movieDTO.overview = this.overview;
            movieDTO.title = this.title;
            movieDTO.rating = this.rating;
            movieDTO.director = this.director;
            movieDTO.minutes = this.minutes;
            movieDTO.genres = this.genres;
            movieDTO.actors = this.actors;
            movieDTO.id = this.id;
            return movieDTO;
        }
    }
}
