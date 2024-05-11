package costa.costa.luiz.unit.movie.adapter.out.persistence;

import costa.costa.luiz.unit.movie.application.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryJPA extends JpaRepository<Movie, Long> {
}
