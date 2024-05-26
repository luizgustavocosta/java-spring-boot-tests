package com.costa.luiz.tropicalflix.genre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
                                PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size))));
    }

    @Operation(summary = "Get a genre by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the genre",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenreDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Genre not found",
                    content = @Content)})
    @GetMapping("{id}")
    ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Save a genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genre created",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GenreDTO.class))})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody GenreDTO genreDTO) {
        service.save(genreDTO);
    }
}
