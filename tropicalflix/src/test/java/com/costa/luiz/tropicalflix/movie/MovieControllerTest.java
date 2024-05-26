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

        when(movieService.findById(1L)).thenReturn(new MovieDTO(null,
                title, 2000, 88, 6.8, null, null, null, null, null, null));

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

    private List<MovieDTO> createMovies() {
        return List.of(
                new MovieDTO(1L, "Toy Story", 1990, 100, 5, null, null, null, null, null, null),
                new MovieDTO(2L, "Toy Story II", 1991, 100, 6, null, null, null, null, null, null),
                new MovieDTO(3L, "Toy Story III", 1992, 100,
                        8.3, null, null, null, null, null, null
                ));

    }
}