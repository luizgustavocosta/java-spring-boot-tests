package costa.costa.luiz.unit.movie.application.domain.service;

import costa.costa.luiz.unit.movie.adapter.out.persistence.MovieRepositoryJPA;
import costa.costa.luiz.unit.movie.application.domain.model.Movie;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepositoryJPA movieRepositoryJPA;

    public MovieService(MovieRepositoryJPA movieRepositoryJPA) {
        this.movieRepositoryJPA = movieRepositoryJPA;
    }

    public Movie findById(Long id) {
        return movieRepositoryJPA.findById(id).orElseThrow();
    }

    public List<Movie> findAll(PageRequest pageRequest) {
        return movieRepositoryJPA.findAll(pageRequest).toList();
    }

    public long countTotalItems() {
        return movieRepositoryJPA.count();
    }
}
