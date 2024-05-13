package costa.costa.luiz.tropicalflix.actor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service class ActorsService {

    private final ActorRepositoryJPA actorRepositoryJPA;

    ActorsService(ActorRepositoryJPA actorRepositoryJPA) {
        this.actorRepositoryJPA = actorRepositoryJPA;
    }

    Set<Actor> findAll() {
        return null;
    }

    List<Actor> findAll(PageRequest pageRequest) {
        return actorRepositoryJPA.findAll(pageRequest).toList();
    }

    long count() {
        return actorRepositoryJPA.count();
    }

    Actor findById() {
        return null;
    }
}
