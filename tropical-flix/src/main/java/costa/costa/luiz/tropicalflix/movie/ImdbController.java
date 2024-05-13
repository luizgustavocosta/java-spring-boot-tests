package costa.costa.luiz.tropicalflix.movie;

import costa.costa.luiz.tropicalflix.actor.Actor;
import costa.costa.luiz.tropicalflix.actor.ActorRepositoryJPA;
import costa.costa.luiz.tropicalflix.genre.Genre;
import costa.costa.luiz.tropicalflix.genre.GenreDTO;
import costa.costa.luiz.tropicalflix.genre.GenreRepositoryJPA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ui")
public class ImdbController {

    private static final String MESSAGE = "message";

    @Value("${application.pagination.page-size:10}")
    private int pageSize;
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

//    @GetMapping("/actors")
//    ModelAndView allActors(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String size) {
//        List<Actor> actors = actorRepositoryJPA.findAllByOrderByNameAsc(PageRequest.of(Integer.parseInt(page) - 1,
//                Integer.parseInt(size)));
//        long totalItens = actorRepositoryJPA.count();
//        ModelAndView model = new ModelAndView("actors");
//        model.addObject("currentPage", Integer.parseInt(page));
//        model.addObject("totalItems", totalItens);
//        model.addObject("totalPages", (totalItens / pageSize));
//        model.addObject("pageSize", pageSize);
//        model.addObject("actors", actors);
//
//        return model;
//    }

    @GetMapping("/genres")
    ModelAndView allGenres(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String size) {
        var genres = genreRepositoryJPA.findAllByOrderByName(PageRequest.of(Integer.parseInt(page) - 1,
                        Integer.parseInt(size))).stream()
                .map(genre -> new GenreDTO(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
        long totalItens = genreRepositoryJPA.count();
        ModelAndView model = new ModelAndView("genres");
        model.addObject("currentPage", Integer.parseInt(page));
        model.addObject("totalItems", totalItens);
        model.addObject("totalPages", (totalItens / pageSize));
        model.addObject("pageSize", pageSize);
        model.addObject("genres", genres);
        return model;
    }

    @GetMapping("/movies")
    ModelAndView moviesByPage(@RequestParam(defaultValue = "1") String page,
                              @RequestParam(defaultValue = "10") String size) {
        List<MovieDTO> movies = movieRepositoryJPA.findAll(PageRequest.of(Integer.parseInt(page) - 1,
                        Integer.parseInt(size)))
                .stream()
                .map(MovieDTO::convertToDTOPlainValues)
                .toList();
        long totalItens = movieRepositoryJPA.count();
        ModelAndView model = new ModelAndView("movies");
        model.addObject("currentPage", Integer.parseInt(page));
        model.addObject("totalItems", totalItens);
        model.addObject("totalPages", (totalItens / pageSize));
        model.addObject("pageSize", pageSize);
        model.addObject("movies", movies);
        return model;
    }

    @GetMapping("/actors/{id}")
    ModelAndView allActors(@PathVariable String id) {
        return new ModelAndView("edit-actor")
                .addObject("actor",
                        actorRepositoryJPA.findById(Long.valueOf(id)).orElseThrow());
    }

    @GetMapping("/genres/{id}")
    ModelAndView editGenre(@PathVariable String id) {
        return new ModelAndView("edit-genre")
                .addObject("genre",
                        genreRepositoryJPA.findById(Long.valueOf(id)).orElseThrow());
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
        attributes.addFlashAttribute(MESSAGE, "Ator " + actor.getName() + " atualizado com sucesso!");
        return "redirect:/ui/actors";
    }

    @PostMapping("/genres/{id}")
    String updateActor(@PathVariable String id,
                       @ModelAttribute("actor") Actor actor, RedirectAttributes attributes) {

        var genre = genreRepositoryJPA.findById(Long.valueOf(id)).orElseThrow();
        genre.setName(actor.getName());
        genreRepositoryJPA.save(genre);

        attributes.addFlashAttribute(MESSAGE, "Gênero " + genre.getName() + " atualizado com sucesso!");
        return "redirect:/ui/genres";
    }

    @GetMapping("/genres/new")
    public ModelAndView showCreateForm() {
        return new ModelAndView("new-genre").addObject("genre", new GenreDTO(null, null));
    }

    @PostMapping("/genres/new")
    String createGenre(@ModelAttribute("genre") GenreDTO genreDTO, RedirectAttributes attributes) {
        genreRepositoryJPA.save(Genre.GenreBuilder.aGenre().withName(genreDTO.name()).build());
        attributes.addFlashAttribute(MESSAGE, "Gênero " + genreDTO.name() + " criado com sucesso!");
        return "redirect:/ui/genres";
    }

    @GetMapping("/actors/{id}/delete")
    public String deleteActor(@PathVariable String id, RedirectAttributes attributes) {
        actorRepositoryJPA.deleteById(Long.valueOf(id));
        attributes.addFlashAttribute(MESSAGE, "Actor deleted successfully!");
        return "redirect:/ui/actors";
    }

    @GetMapping("/genres/{id}/delete")
    public String deleteGenre(@PathVariable String id, RedirectAttributes attributes) {
        genreRepositoryJPA.deleteById(Long.valueOf(id));
        attributes.addFlashAttribute(MESSAGE, "Genre deleted successfully!");
        return "redirect:/ui/genres";
    }

    @GetMapping("/movies/{id}/delete")
    public String deleteMovie(@PathVariable String id, RedirectAttributes attributes) {
        movieRepositoryJPA.deleteById(Long.valueOf(id));
        attributes.addFlashAttribute(MESSAGE, "Movie deleted successfully!");
        return "redirect:/ui/movies";
    }

    @GetMapping("/actors/new")
    public ModelAndView showCreateActorForm() {
        throw new IllegalStateException("Not ready!", new IllegalStateException());
    }
}
