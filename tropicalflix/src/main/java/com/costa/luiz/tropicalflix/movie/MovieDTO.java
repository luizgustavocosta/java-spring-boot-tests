package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.actor.Actor;
import com.costa.luiz.tropicalflix.genre.Genre;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieDTO(Long id, String title, int releasedYear, int minutes, double rating, String overview,
                       String director, Set<Actor> actors, Set<Genre> genres, String genresAsString,
                       String actorsAsString) { }



