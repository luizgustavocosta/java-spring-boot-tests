package com.costa.luiz.tropicalflix.genre;

import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class GenreService {

    private final GenreRepositoryJPA repositoryJPA;

    GenreService(GenreRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    public List<GenreDTO> findAll(Pageable pageable) {
        return repositoryJPA.findAll(pageable)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public long count() {
        return repositoryJPA.count();
    }

    public GenreDTO save(GenreDTO genre) {
        return this.toDTO(repositoryJPA.save(Genre.GenreBuilder.aGenre()
                .withId(genre.id())
                .withName(genre.name())
                .build()));
    }
    public GenreDTO findById(long id) {
        return repositoryJPA.findById(id)
                .map(this::toDTO)
                .orElseThrow(NonExistingEntity::new);
    }

    public void deleteById(long id) {
        repositoryJPA.deleteById(id);
    }

    GenreDTO toDTO(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }
}
