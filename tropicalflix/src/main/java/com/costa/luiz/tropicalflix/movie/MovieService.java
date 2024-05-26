package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepositoryJPA repository;

    public MovieService(MovieRepositoryJPA repository) {
        this.repository = repository;
    }

    public MovieDTO findById(Long id) {
        return
                repository.findById(id)
                        .map(MovieDTO::toDTO)
                        .orElseThrow(NonExistingEntity::new);
    }

    public List<MovieDTO> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .stream()
                .map(MovieDTO::toDTO)
                .toList();
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
