package costa.costa.luiz.tropicalflix.movie.adapter.in.rest;

import costa.costa.luiz.tropicalflix.movie.Movie;
import costa.costa.luiz.tropicalflix.movie.MovieController;
import costa.costa.luiz.tropicalflix.movie.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void byId() throws Exception {
        var title = "Mad Max";
        var movie = new Movie();
        movie.setTitle(title);
        when(movieService.findById(1L)).thenReturn(movie);
        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andDo(print());

        verify(movieService).findById(1L);
    }

    @Test
    void all() throws Exception {
        var movie = new Movie();
        movie.setTitle("Rocky 1");
        var movies = List.of(movie);
        when(movieService.findAll(any(PageRequest.class))).thenReturn(movies);
        mockMvc.perform(get("/movies"))
                .andExpectAll(
                    status().isOk(),
                    jsonPath("$[0].title").isNotEmpty()
                );
    }
}