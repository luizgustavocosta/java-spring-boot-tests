package com.costa.luiz.tropicalflix.genre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
class GenreControllerSpringBootTest {

    private static final String ENDPOINT = "/api/v1/genres";
    @MockBean
    private GenreService genreService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JacksonTester<GenreDTO> jsonGenreDTO;
    @LocalServerPort
    private int port;


    @Test
    @DisplayName("Find genre by id")
    void findById() {
        var id = 42L;
        var name = "Western";
        given(genreService.findById(id))
                .willReturn(new GenreDTO(id, name));

        var genreResponse = restTemplate
                .getForEntity("/api/v1/genres/" + id, GenreDTO.class);

        assertThat(genreResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(genreResponse.getBody())
                .extracting("id", "name")
                .contains(id, name);

    }

    @Test
    @DisplayName("Create a new genre")
    void create() {
        var genreResponse = restTemplate.postForEntity(ENDPOINT, new GenreDTO(9L, "Sci-fi"), GenreDTO.class);

        assertThat(genreResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}