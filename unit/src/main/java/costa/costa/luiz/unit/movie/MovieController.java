package costa.costa.luiz.unit.movie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepositoryJPA movieRepositoryJPA;

    public MovieController(MovieRepositoryJPA movieRepositoryJPA) {
        this.movieRepositoryJPA = movieRepositoryJPA;
    }

    @GetMapping("/{id}")
    ResponseEntity<Movie> byId(@PathVariable("id") String id) {
        Optional<Movie> optionalMovie = movieRepositoryJPA.findById(Long.valueOf(id));
        return ResponseEntity.of(optionalMovie);
    }

    @GetMapping("/all")
    ResponseEntity<List<Movie>> all() {
        return ResponseEntity.of(Optional.of(movieRepositoryJPA.findAll()));
    }
}
