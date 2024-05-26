package com.costa.luiz.tropicalflix.actor;

import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ActorService {

    private final ActorRepositoryJPA repositoryJPA;

    ActorService(ActorRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    List<ActorDTO> findAll(PageRequest pageRequest) {
        return repositoryJPA.findAll(pageRequest)
                .stream()
                .map(ActorDTO::toDTO)
                .toList();
    }

    long count() {
        return repositoryJPA.count();
    }

    ActorDTO findById(Long id) {
        return repositoryJPA.findById(id)
                .map(ActorDTO::toDTO)
                .orElseThrow(NonExistingEntity::new);
    }

    public void deleteById(Long id) {
        repositoryJPA.deleteById(id);
    }

    public void save(ActorDTO actor) {
        repositoryJPA.save(Actor.ActorBuilder.anActor().withId(actor.id()).withName(actor.name()).build());
    }

    private ActorDTO toDTO() {
        return null;
    }

    private Actor toActor(ActorDTO actorDTO) {
        return null;
    }
}
