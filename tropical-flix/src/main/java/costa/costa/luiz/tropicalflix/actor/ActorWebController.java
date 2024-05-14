package costa.costa.luiz.tropicalflix.actor;

import costa.costa.luiz.tropicalflix.shared.ThymeleafPagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ui/actors")
class ActorWebController implements ThymeleafPagination {

    private final ActorService service;

    public ActorWebController(ActorService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView findAll(@RequestParam(defaultValue = "1") String page,
                         @RequestParam(defaultValue = "10") String size) {
        int sizeAsInt = Integer.parseInt(size);
        List<Actor> actors = service.findAll(PageRequest.of(Integer.parseInt(page) - 1, sizeAsInt));
        long totalItens = service.count();
        return
                addPaginationToView("actors",
                        Integer.parseInt(page),
                        totalItens,
                        sizeAsInt,
                        "actors",
                        actors);
    }

    @GetMapping("/new")
    public ModelAndView showCreateActorForm() {
        throw new IllegalStateException("{ Feature not ready}", new IllegalStateException());
    }

    @GetMapping("/delete/{id}")
    public String deleteActor(@PathVariable String id, @ModelAttribute("actor") Actor actor, RedirectAttributes attributes) {
        service.deleteById(Long.parseLong(id));
        attributes.addFlashAttribute("message", "Ator excluído com sucesso!");
        return "redirect:/ui/actors";
    }

    @PostMapping("/{id}")
    String updateActor(@PathVariable String id,
                       @ModelAttribute("actor") Actor actor,
                       RedirectAttributes attributes) {

        var currentActor = service.findById(Long.valueOf(id));
        currentActor.setName(actor.getName());

        service.save(currentActor);

        attributes.addFlashAttribute("message", "Ator " + actor.getName() + " atualizado com sucesso!");
        return "redirect:/ui/actors";
    }

    @GetMapping("/{id}")
    ModelAndView byId(@PathVariable String id) {
        return new ModelAndView("edit-actor")
                .addObject("actor",
                        service.findById(Long.valueOf(id)));
    }
}
