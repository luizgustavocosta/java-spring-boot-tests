package costa.costa.luiz.tropicalflix.actor;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/actors")
class ActorController {

    private final ActorsService actorsService;

    public ActorController(ActorsService actorsService) {
        this.actorsService = actorsService;
    }

    @GetMapping("/api/v1/actors")
    ResponseEntity<List<Actor>> findAll(HttpServletResponse response, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String size) {
        return ResponseEntity.ok(
                actorsService.findAll(
                        PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size))));
    }
    @GetMapping("/ui/actors")
    ModelAndView findAllForUI(HttpServletResponse response, @RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String size) {
        List<Actor> actors = actorsService.findAll(PageRequest.of(Integer.parseInt(page) - 1,
                Integer.parseInt(size)));
        long totalItens = actorsService.count();
        ModelAndView model = new ModelAndView("actors");
        model.addObject("currentPage", Integer.parseInt(page));
        model.addObject("totalItems", totalItens);
        model.addObject("totalPages", (totalItens / 10));
        model.addObject("pageSize", 10);
        model.addObject("actors", actors);
        return model;
    }
}
