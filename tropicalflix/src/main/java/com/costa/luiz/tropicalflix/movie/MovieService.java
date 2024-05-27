package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.actor.Actor;
import com.costa.luiz.tropicalflix.genre.Genre;
import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepositoryJPA repository;

    public MovieService(MovieRepositoryJPA repository) {
        this.repository = repository;
    }

    public MovieDTO findById(Long id) {
        return
                repository.findById(id)
                        .map(this::toDTO)
                        .orElseThrow(NonExistingEntity::new);
    }

    public List<MovieDTO> findAll(Pageable pageRequest) {
        return repository.findAll(pageRequest)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<MovieDTO> findAllForUI(Pageable pageRequest) {
        return repository.findAll(pageRequest)
                .stream()
                .map(this::convertToDTOPlainValues)
                .toList();
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private MovieDTO toDTO(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getReleasedYear(), movie.getMinutes(),
                movie.getRating(), movie.getOverview(), movie.getDirector(), movie.getActors(),
                movie.getGenres(), null, null);
    }

    private MovieDTO convertToDTOPlainValues(Movie movie) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getReleasedYear(), movie.getMinutes(),
                movie.getRating(), movie.getOverview(), movie.getDirector(), null, null,
                movie.getGenres().stream().map(Genre::getName).sorted().collect(Collectors.joining(",")),
                movie.getActors().stream().map(Actor::getName).sorted().collect(Collectors.joining(",")));
    }
}
