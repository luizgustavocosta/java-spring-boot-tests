package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.actor.Actor;
import com.costa.luiz.tropicalflix.genre.Genre;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieDTO(Long id, String title, int releasedYear, int minutes, double rating, String overview,
                String director, Set<Actor> actors, Set<Genre> genres, String genresAsString,
                String actorsAsString) {
    static MovieDTO toDTO(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getReleasedYear(), movie.getMinutes(),
                movie.getRating(), movie.getOverview(), movie.getDirector(), movie.getActors(),
                movie.getGenres(), null, null);
    }

    static MovieDTO convertToDTOPlainValues(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getReleasedYear(), movie.getMinutes(),
                movie.getRating(), movie.getOverview(), movie.getDirector(), null, null,
                movie.getActors().stream().map(Actor::getName).collect(Collectors.joining(",")),
                movie.getGenres().stream().map(Genre::getName).collect(Collectors.joining(",")));
    }
}


