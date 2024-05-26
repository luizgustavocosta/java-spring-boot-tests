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

    static MovieDTO convertToDTOPlainValues(MovieDTO movie) {
        return new MovieDTO(movie.id(), movie.title(), movie.releasedYear(), movie.minutes(),
                movie.rating(), movie.overview(), movie.director(), null, null,
                movie.actors().stream().map(Actor::getName).collect(Collectors.joining(",")),
                movie.genres().stream().map(Genre::getName).collect(Collectors.joining(",")));
    }
}



