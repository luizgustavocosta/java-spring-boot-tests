package costa.costa.luiz.tropicalflix.movie;

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
        return ResponseEntity.ok(MovieDTO.convertToDTO(service.findById(Long.valueOf(id))));
    }

    @GetMapping
    ResponseEntity<List<MovieDTO>> all(@RequestParam(defaultValue = "1") String page,
                                       @RequestParam(defaultValue = "10") String size) {
        List<MovieDTO> movies = service.findAll(PageRequest.of(Integer.parseInt(page) - 1,
                        Integer.parseInt(size)))
                .stream()
                .map(MovieDTO::convertToDTO)
                .toList();
        return ResponseEntity.ok(movies);
    }
}
