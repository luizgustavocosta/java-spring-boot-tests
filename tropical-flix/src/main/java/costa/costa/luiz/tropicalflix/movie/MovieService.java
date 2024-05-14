package costa.costa.luiz.tropicalflix.movie;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepositoryJPA repository;

    public MovieService(MovieRepositoryJPA repository) {
        this.repository = repository;
    }

    public Movie findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Movie> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest).toList();
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
