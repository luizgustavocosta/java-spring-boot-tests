package com.costa.luiz.tropicalflix.genre;

import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GenreService {

    private final GenreRepositoryJPA repositoryJPA;

    GenreService(GenreRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    public List<Genre> findAll(PageRequest pageable) {
        return repositoryJPA.findAll(pageable).toList();
    }

    public long count() {
        return repositoryJPA.count();
    }

    public Genre save(Genre genre) {
        return repositoryJPA.save(genre);
    }

    public Genre findById(long id) {
        return repositoryJPA.findById(id).orElseThrow(NonExistingEntity::new);
    }

    public void deleteById(long id) {
        repositoryJPA.deleteById(id);
    }
}
