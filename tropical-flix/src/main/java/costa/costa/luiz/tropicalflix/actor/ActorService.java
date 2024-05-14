package costa.costa.luiz.tropicalflix.actor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ActorService {

    private final ActorRepositoryJPA repositoryJPA;

    ActorService(ActorRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    List<Actor> findAll(PageRequest pageRequest) {
        return repositoryJPA.findAll(pageRequest).toList();
    }

    long count() {
        return repositoryJPA.count();
    }

    Actor findById(Long id) {
        return repositoryJPA.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        repositoryJPA.deleteById(id);
    }

    public void save(Actor actor) {
        repositoryJPA.save(actor);
    }
}
