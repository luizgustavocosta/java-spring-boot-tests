package com.costa.luiz.tropicalflix.genre;

import com.costa.luiz.tropicalflix.shared.ExceptionHandlerController;
import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(MockitoExtension.class)
class GenreControllerTest {

    // Standalone
    private MockMvc mockMvc;
    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    private final String ENDPOINT = "/api/v1/genres";

    private JacksonTester<GenreDTO> jsonGenre;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(genreController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    @DisplayName("Find all genres")
    void findAll() throws Exception {
        given(genreService.findAll(any(PageRequest.class))).willReturn(genres());

        MockHttpServletResponse response = mockMvc.perform(
                get(ENDPOINT).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualToIgnoringWhitespace("""
                [
                    {"id":1,"name":"Action"},
                    {"id":2,"name":"Comedy"}
                ]
                """);
    }


    @Test
    @DisplayName("Find existing genre by id")
    void findExistingGenreById() throws Exception {
        var genre = new GenreDTO(10L, "Terror");
        given(genreService.findById(any(Long.class))).willReturn(genre);

        var response = mockMvc.perform(
                get(ENDPOINT + "/10")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(jsonGenre.parse(response.getContentAsString()).getObject().name()).isEqualTo("Terror");
    }

    @Test
    @DisplayName("Find by a non-existing genre")
    void findByANonExistingGenre() throws Exception {
        given(genreService.findById(any(Long.class))).willThrow(new NonExistingEntity());

        var response = mockMvc.perform(
                get(ENDPOINT + "/99")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Create a new genre")
    void create() throws Exception {
        var response = mockMvc.perform(
                        post(ENDPOINT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonGenre.write(new GenreDTO(null, "Cartoon")).getJson()))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var genreServiceArgumentCaptor = ArgumentCaptor.forClass(GenreDTO.class);
        verify(genreService).save(genreServiceArgumentCaptor.capture());
        assertThat(genreServiceArgumentCaptor.getValue()).extracting("id", "name").isNotNull();

    }

    List<GenreDTO> genres() {
        return List.of(
                new GenreDTO(1L, "Action"),
                new GenreDTO(2L, "Comedy")
        );
    }
}