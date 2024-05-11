package costa.costa.luiz.unit.movie.adapter.out.persistence;

import costa.costa.luiz.unit.movie.application.domain.model.Genre;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepositoryJPA extends JpaRepository<Genre, Long> {

    List<Genre> findAllByOrderByName(PageRequest pageRequest);

}
