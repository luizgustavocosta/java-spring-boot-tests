package costa.costa.luiz.unit.movie.adapter.in.rest;

import costa.costa.luiz.unit.movie.application.domain.model.Actor;
import costa.costa.luiz.unit.movie.application.domain.model.Genre;

import java.util.Set;

public record MovieDTO(Long id, String title, int releasedYear, int minutes, double rating, String overview,
                       String director, Set<Actor> actors, Set<Genre> genres) {
}

