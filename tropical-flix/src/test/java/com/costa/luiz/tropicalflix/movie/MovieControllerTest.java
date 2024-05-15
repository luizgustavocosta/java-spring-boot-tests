package com.costa.luiz.tropicalflix.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("test")
class MovieControllerTest {

    private static final String URL_TEMPLATE_V1 = "/api/v1/movies";
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
        mockMvc.perform(get(URL_TEMPLATE_V1 + "/1"))
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
        mockMvc.perform(get(URL_TEMPLATE_V1))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].title").isNotEmpty()
                );
    }
}