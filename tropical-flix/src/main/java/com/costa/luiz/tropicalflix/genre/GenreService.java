package com.costa.luiz.tropicalflix.genre;

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

    public void save(Genre genre) {
        repositoryJPA.save(genre);
    }

    public Genre findById(long id) {
        return repositoryJPA.findById(id).orElseThrow();
    }

    public void deleteById(long id) {
        repositoryJPA.deleteById(id);
    }
}
