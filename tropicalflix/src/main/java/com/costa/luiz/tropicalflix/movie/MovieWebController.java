package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.shared.ThymeleafPagination;
import com.costa.luiz.tropicalflix.shared.UIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ui/movies")
class MovieWebController implements ThymeleafPagination {

    private final MovieService service;

    @Value("${application.pagination.page-size:10}")
    private int pageSize;

    MovieWebController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView moviesByPage(@RequestParam(defaultValue = "1") String page,
                              @RequestParam(defaultValue = "10") String size) {
        try {
            var movies = service.findAll(PageRequest.of(Integer.parseInt(page) - 1,
                            Integer.parseInt(size)))
                    .stream()
                    .map(MovieDTO::convertToDTOPlainValues)
                    .toList();
            long totalItens = service.count();
            var model = new ModelAndView("movies");
            model.addObject("currentPage", Integer.parseInt(page));
            model.addObject("totalItems", totalItens);
            model.addObject("totalPages", (totalItens / pageSize));
            model.addObject("pageSize", pageSize);
            model.addObject("movies", movies);
            return model;
        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteMovie(@PathVariable String id, RedirectAttributes attributes) {
        try {
            service.deleteById(Long.valueOf(id));
            attributes.addFlashAttribute("message", "Movie deleted successfully!");
            return "redirect:/ui/movies";
        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }
}
