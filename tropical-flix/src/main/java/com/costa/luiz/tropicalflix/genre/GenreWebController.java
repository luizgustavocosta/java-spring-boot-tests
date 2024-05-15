package com.costa.luiz.tropicalflix.genre;

import com.costa.luiz.tropicalflix.actor.Actor;
import com.costa.luiz.tropicalflix.shared.ThymeleafPagination;
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

@Controller
@RequestMapping("/ui/genres")
class GenreWebController implements ThymeleafPagination {

    private final GenreService service;

    GenreWebController(GenreService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView findAll(@RequestParam(defaultValue = "1") String page,
                         @RequestParam(defaultValue = "10") String size) {
        int sizeAsInt = Integer.parseInt(size);
        var genres = service.findAll(PageRequest.of(Integer.parseInt(page) - 1, sizeAsInt));
        long totalItens = service.count();
        return
                addPaginationToView("genres",
                        Integer.parseInt(page),
                        totalItens,
                        sizeAsInt,
                        "genres",
                        genres);
    }

    @PostMapping("/{id}")
    String updateGenre(@PathVariable String id,
                       @ModelAttribute("actor") Actor actor, RedirectAttributes attributes) {

        var genre = service.findById(Long.parseLong(id));
        genre.setName(actor.getName());
        service.save(genre);

        attributes.addFlashAttribute("message", "Gênero " + genre.getName() + " atualizado com sucesso!");
        return "redirect:/ui/genres";
    }

    @GetMapping("/{id}")
    ModelAndView editGenre(@PathVariable String id) {
        return new ModelAndView("edit-genre")
                .addObject("genre",
                        service.findById(Long.parseLong(id)));
    }

    @GetMapping("/new")
    public ModelAndView showCreateForm() {
        return new ModelAndView("new-genre").addObject("genre", new GenreDTO(null, null));
    }

    @PostMapping("/new")
    String createGenre(@ModelAttribute("genre") GenreDTO genreDTO, RedirectAttributes attributes) {
        service.save(Genre.GenreBuilder.aGenre().withName(genreDTO.name()).build());
        attributes.addFlashAttribute("message", "Gênero " + genreDTO.name() + " criado com sucesso!");
        return "redirect:/ui/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable String id, RedirectAttributes attributes) {
        long genreId = Long.parseLong(id);
        var genre = service.findById(genreId);
        service.deleteById(genreId);
        attributes.addFlashAttribute("message", "Gênero " + genre.getName() + " excluído com sucesso!");
        return "redirect:/ui/genres";
    }

}
