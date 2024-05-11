package costa.costa.luiz.unit.movie.adapter.in.rest;

import costa.costa.luiz.unit.movie.application.domain.model.Movie;
import costa.costa.luiz.unit.movie.application.domain.service.MovieService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    ResponseEntity<MovieDTO> byId(@PathVariable("id") String id) {
        Movie movie = service.findById(Long.valueOf(id));
        MovieDTO movieDTO = new MovieDTO(movie.getId(), movie.getTitle(), movie.getReleasedYear(), movie.getMinutes(),
                movie.getRating(), movie.getOverview(), movie.getDirector(), movie.getActors(), movie.getGenres());
        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping
    ResponseEntity<List<MovieDTO>> all(@RequestParam(defaultValue = "1") String page,
                                       @RequestParam(defaultValue = "10") String size) {
        List<MovieDTO> movies = service.findAll(PageRequest.of(Integer.parseInt(page) - 1,
                        Integer.parseInt(size)))
                .stream()
                .map(movie ->
                        new MovieDTO(
                                movie.getId(),
                                movie.getTitle(),
                                movie.getReleasedYear(),
                                movie.getMinutes(),
                                movie.getRating(),
                                movie.getOverview(),
                                movie.getDirector(),
                                movie.getActors(),
                                movie.getGenres()))
                .toList();
        return ResponseEntity.ok(movies);
    }
}
