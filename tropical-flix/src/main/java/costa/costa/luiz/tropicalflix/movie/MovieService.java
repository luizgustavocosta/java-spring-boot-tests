package costa.costa.luiz.tropicalflix.movie;

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

    public Movie save(costa.costa.luiz.tropicalflix.movie.MovieDTO movieDTO) {
        throw new IllegalStateException("Not implemented!");
    }


}
