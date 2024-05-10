package costa.costa.luiz.unit.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepositoryJPA extends JpaRepository<Actor, Long> {

    Actor findByName(String name);
}
