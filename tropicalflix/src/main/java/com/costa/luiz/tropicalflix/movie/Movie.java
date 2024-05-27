package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.actor.Actor;
import com.costa.luiz.tropicalflix.genre.Genre;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Objects;
import java.util.Set;

@Entity(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int releasedYear;
    private int minutes;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;
    private double rating;

    @Column(length = 1_000)
    private String overview;
    private String director;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }


    public static final class MovieBuilder {
        private Long id;
        private String title;
        private int releasedYear;
        private int minutes;
        private Set<Genre> genres;
        private double rating;
        private String overview;
        private String director;
        private Set<Actor> actors;

        private MovieBuilder() {
        }

        public static MovieBuilder aMovie() {
            return new MovieBuilder();
        }

        public MovieBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public MovieBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder withReleasedYear(int releasedYear) {
            this.releasedYear = releasedYear;
            return this;
        }

        public MovieBuilder withMinutes(int minutes) {
            this.minutes = minutes;
            return this;
        }

        public MovieBuilder withGenres(Set<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public MovieBuilder withRating(double rating) {
            this.rating = rating;
            return this;
        }

        public MovieBuilder withOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public MovieBuilder withDirector(String director) {
            this.director = director;
            return this;
        }

        public MovieBuilder withActors(Set<Actor> actors) {
            this.actors = actors;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.setId(id);
            movie.setTitle(title);
            movie.setReleasedYear(releasedYear);
            movie.setMinutes(minutes);
            movie.setGenres(genres);
            movie.setRating(rating);
            movie.setOverview(overview);
            movie.setDirector(director);
            movie.setActors(actors);
            return movie;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
