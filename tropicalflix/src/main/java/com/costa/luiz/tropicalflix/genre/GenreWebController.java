package com.costa.luiz.tropicalflix.genre;

import com.costa.luiz.tropicalflix.shared.ThymeleafPagination;
import com.costa.luiz.tropicalflix.shared.UIException;
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

    private static final String REDIRECT_UI_GENRES = "redirect:/ui/genres";
    private static final String MESSAGE = "message";
    private static final String GENRE = "Gênero ";
    private final GenreService service;

    GenreWebController(GenreService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView findAll(@RequestParam(defaultValue = "1") String page,
                         @RequestParam(defaultValue = "10") String size) {
        try {
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
        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }

    @PostMapping("/{id}")
    String updateGenre(@PathVariable String id,
                       @ModelAttribute("genre") GenreDTO genreDTO, RedirectAttributes attributes) {
        try {
            service.save(genreDTO);
            attributes.addFlashAttribute(MESSAGE, GENRE + genreDTO.name() + " atualizado com sucesso!");
            return REDIRECT_UI_GENRES;
        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }

    @GetMapping("/{id}")
    ModelAndView editGenre(@PathVariable String id) {
        try {
            return new ModelAndView("edit-genre")
                    .addObject("genre",
                            service.findById(Long.parseLong(id)));

        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }

    @GetMapping("/new")
    public ModelAndView showCreateForm() {
        return new ModelAndView("new-genre").addObject("genre", new GenreDTO(null, null));
    }

    @PostMapping("/new")
    String createGenre(@ModelAttribute("genre") GenreDTO genreDTO, RedirectAttributes attributes) {
        try {
            service.save(genreDTO);
            attributes.addFlashAttribute(MESSAGE, GENRE + genreDTO.name() + " criado com sucesso!");
            return REDIRECT_UI_GENRES;
        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable String id, RedirectAttributes attributes) {
        try {
            service.deleteById(Long.parseLong(id));
            attributes.addFlashAttribute(MESSAGE, GENRE +" excluído com sucesso!");
            return REDIRECT_UI_GENRES;
        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }

}
