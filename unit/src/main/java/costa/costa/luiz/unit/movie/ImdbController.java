package costa.costa.luiz.unit.movie;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final ActorRepositoryJPA actorRepositoryJPA;

    public ImdbController(ActorRepositoryJPA actorRepositoryJPA) {
        this.actorRepositoryJPA = actorRepositoryJPA;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/actors")
    ModelAndView allActors() {
        Pageable pagination = PageRequest.of(0, 10);
        return new ModelAndView("actors")
                .addObject("actors",
                        actorRepositoryJPA.findAll(pagination));
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
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        actorRepositoryJPA.deleteById(Long.valueOf(id));
        attributes.addFlashAttribute("message", "Actor deleted successfully!");
        return "redirect:/actors";
    }
}
