package costa.costa.luiz.unit.movie.adapter.out.persistence;

import costa.costa.luiz.unit.movie.application.domain.model.Actor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepositoryJPA extends JpaRepository<Actor, Long> {

    List<Actor> findAllByOrderByNameDesc(PageRequest pageRequest);
}
