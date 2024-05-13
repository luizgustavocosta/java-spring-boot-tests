package costa.costa.luiz.tropicalflix.actor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepositoryJPA extends JpaRepository<Actor, Long> {

    List<Actor> findAllByOrderByNameAsc(PageRequest pageRequest);
}
