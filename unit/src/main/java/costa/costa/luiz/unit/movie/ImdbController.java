package costa.costa.luiz.unit.movie;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui")
public class ImdbController {

    private final MovieRepositoryJPA movieRepositoryJPA;
    private final ActorRepositoryJPA actorRepositoryJPA;
    private final GenreRepositoryJPA genreRepositoryJPA;

    public ImdbController(MovieRepositoryJPA movieRepositoryJPA,
                          ActorRepositoryJPA actorRepositoryJPA,
                          GenreRepositoryJPA genreRepositoryJPA) {
        this.movieRepositoryJPA = movieRepositoryJPA;
        this.actorRepositoryJPA = actorRepositoryJPA;
        this.genreRepositoryJPA = genreRepositoryJPA;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/actors")
    ModelAndView allActors() {
        return new ModelAndView("actors")
                .addObject("actors",
                        actorRepositoryJPA.findAll(PageRequest.of(0, 10)));
    }

    @GetMapping("/genres")
    ModelAndView allGenres() {
        return new ModelAndView("genres")
                .addObject("genres",
                        genreRepositoryJPA.findAll());
    }

    @GetMapping("/movies")
    ModelAndView allMovies() {
        List<MovieDTO> movies = movieRepositoryJPA.findAll(PageRequest.of(0, 10))
                .stream()
                .map(movie ->
                        MovieDTO.MovieDTOBuilder.aMovieDTO()
                                .withId(movie.getId())
                                .withTitle(movie.getTitle())
                                .withRating(movie.getRating())
                                .withMinutes(movie.getMinutes())
                                .withOverview(movie.getOverview())
                                .withDirector(movie.getDirector())
                                .withReleasedYear(movie.getReleasedYear())
                                .withActors(movie.getActors()
                                        .stream()
                                        .map(Actor::getName)
                                        .collect(Collectors.joining(",")))
                                .withGenres(movie.getGenres()
                                        .stream()
                                        .map(Genre::getName)
                                        .collect(Collectors.joining(",")))
                                .build()
                )
                .toList();
        return new ModelAndView("movies").addObject("movies", movies);
    }

    @GetMapping("/actors/{id}")
    ModelAndView allActors(@PathVariable String id) {
        return new ModelAndView("edit-actor")
                .addObject("actor",
                        actorRepositoryJPA.findById(Long.valueOf(id)).get());
    }

    @PostMapping("/actors/{id}")
    String updateActor(@PathVariable String id,
                       @ModelAttribute("actor") Actor actor, BindingResult result, RedirectAttributes attributes) {

        Optional<Actor> optionalActor = actorRepositoryJPA.findById(Long.valueOf(id));
        if (optionalActor.isPresent()) {
            Actor dbActor = optionalActor.get();
            dbActor.setName(actor.getName());
            actorRepositoryJPA.save(dbActor);
        }
        attributes.addFlashAttribute("message", "Actor updated successfully!");
        return "redirect:/actors";
    }

    @GetMapping("/actors/{id}/delete")
    public String deleteActor(@PathVariable String id, RedirectAttributes attributes) {
        actorRepositoryJPA.deleteById(Long.valueOf(id));
        attributes.addFlashAttribute("message", "Actor deleted successfully!");
        return "redirect:/ui/actors";
    }

    @GetMapping("/genres/{id}/delete")
    public String deleteGenre(@PathVariable String id, RedirectAttributes attributes) {
        genreRepositoryJPA.deleteById(Long.valueOf(id));
        attributes.addFlashAttribute("message", "Genre deleted successfully!");
        return "redirect:/ui/genres";
    }

    @GetMapping("/movies/{id}/delete")
    public String deleteMovie(@PathVariable String id, RedirectAttributes attributes) {
        movieRepositoryJPA.deleteById(Long.valueOf(id));
        attributes.addFlashAttribute("message", "Movie deleted successfully!");
        return "redirect:/ui/movies";
    }
}
