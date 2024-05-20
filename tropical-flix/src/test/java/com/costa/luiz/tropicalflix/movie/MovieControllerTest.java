package com.costa.luiz.tropicalflix.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MovieControllerTest {

    private static final String ENDPOINT = "/api/v1/movies";

    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Find movie by id")
    void byId() throws Exception {
        var title = "Mad Max";

        when(movieService.findById(1L)).thenReturn(Movie.MovieBuilder.aMovie()
                .withTitle(title)
                .withMinutes(88)
                .withRating(6.8)
                .build());

        mockMvc.perform(get(ENDPOINT + "/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.title").value(title),
                        jsonPath("$.minutes").isNumber(),
                        jsonPath("$.rating").exists()
                )
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Find all movies")
    void all() throws Exception {
        when(movieService.findAll(any(PageRequest.class))).thenReturn(createMovies());
        mockMvc.perform(get(ENDPOINT))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].title").isNotEmpty(),
                        jsonPath("$[1].title").value("Toy Story II"),
                        jsonPath("$[2].rating").isNumber(),
                        jsonPath("$[2].rating").value(8.3)
                );
    }

    private List<Movie> createMovies() {
        return List.of(
                Movie.MovieBuilder.aMovie().withId(1L).withTitle("Toy Story").build(),
                Movie.MovieBuilder.aMovie().withId(2L).withTitle("Toy Story II").build(),
                Movie.MovieBuilder.aMovie().withId(3L).withTitle("Toy Story III")
                        .withRating(8.3)
                        .build()
        );

    }
}