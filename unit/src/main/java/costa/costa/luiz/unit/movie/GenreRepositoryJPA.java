package costa.costa.luiz.unit.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepositoryJPA extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
