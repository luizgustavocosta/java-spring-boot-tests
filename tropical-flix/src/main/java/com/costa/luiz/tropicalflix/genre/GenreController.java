package com.costa.luiz.tropicalflix.genre;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@Cacheable("genres")
class GenreController {

    private final GenreService service;

    GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<GenreDTO>> findAll(@RequestParam(defaultValue = "1") String page,
                                           @RequestParam(defaultValue = "10") String size) {
        return ResponseEntity.ok(
                service.findAll(
                                PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size)))
                        .stream()
                        .map(GenreDTO::toDTO)
                        .toList());
    }

    @GetMapping("{id}")
    ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                GenreDTO.toDTO(service.findById(id))
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody GenreDTO genreDTO) {
        service.save(GenreDTO.toGenre(genreDTO));
    }

}
