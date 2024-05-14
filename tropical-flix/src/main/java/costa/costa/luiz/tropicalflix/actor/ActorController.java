package costa.costa.luiz.tropicalflix.actor;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    ResponseEntity<List<ActorDTO>> findAll(@RequestParam(defaultValue = "1") String page,
                                           @RequestParam(defaultValue = "10") String size) {
        return ResponseEntity.ok(
                actorService.findAll(
                        PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size)))
                        .stream()
                        .map(ActorDTO::toDTO)
                        .toList());
    }
}
