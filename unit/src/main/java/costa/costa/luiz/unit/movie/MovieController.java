package costa.costa.luiz.unit.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepositoryJPA movieRepositoryJPA;
    private final GenreRepositoryJPA genreRepositoryJPA;

    public MovieController(MovieRepositoryJPA movieRepositoryJPA, GenreRepositoryJPA genreRepositoryJPA) {
        this.movieRepositoryJPA = movieRepositoryJPA;
        this.genreRepositoryJPA = genreRepositoryJPA;
    }

//    @GetMapping("/{id}")
//    ResponseEntity<Movie> byId(@PathVariable("id") String id) {
//        Optional<Movie> optionalMovie = movieRepositoryJPA.findById(Long.valueOf(id));
//        return ResponseEntity.of(optionalMovie);
//    }

    @GetMapping("/all")
    ResponseEntity<Page<Movie>> all() {
        //return ResponseEntity.of(Optional.of(movieRepositoryJPA.findAll()));
        Pageable paging = PageRequest.of(0, 2);
        return ResponseEntity.of(Optional.of(movieRepositoryJPA.findAll(paging)));
    }

    @GetMapping("/genres")
    public ModelAndView showGenres() {
        List<Genre> genres = genreRepositoryJPA.findAll();
        return new ModelAndView("genres").addObject("genres", genres);
    }
}
