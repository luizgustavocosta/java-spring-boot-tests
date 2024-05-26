package com.costa.luiz.tropicalflix.actor;

import com.costa.luiz.tropicalflix.shared.ExceptionHandlerController;
import com.costa.luiz.tropicalflix.shared.UIException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActorWebController.class)
class ActorWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ActorService actorService;

    @MockBean
    ExceptionHandlerController exceptionHandlerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final static String ENDPOINT = "/ui/actors";

    @Test
    @DisplayName("Find all actors")
    void findAll() throws Exception {
        when(actorService.findAll(any()))
                .thenReturn(List.of(new ActorDTO(1L, "Chase")));
        this.mockMvc
                .perform(get(ENDPOINT))
                .andExpect(status().isOk())
//                .andExpect(view().name("actors"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalItems"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("pageSize"));
    }

    @Test
    @DisplayName("Create a new actor")
    void newActor() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(get(ENDPOINT + "/new"))
                .andReturn();

        verify(exceptionHandlerController).uiExceptionHander(any(UIException.class));
        assertNotNull(mvcResult.getResolvedException());
    }

    @Test
    @DisplayName("Delete an actor by id")
    void deleteById() throws Exception {
        this.mockMvc
                .perform(get(ENDPOINT + "/delete/10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"));
    }

    @Test
    @DisplayName("Update an actor")
    void updateAnActor() throws Exception {
        long id = 42L;
        var actor = new ActorDTO(id, "ABCs");
        this.mockMvc
                .perform(
                        post(ENDPOINT + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                                .flashAttr("actor", actor))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Find the actor by id")
    void byId() throws Exception {
        when(actorService.findById(any()))
                .thenReturn(new ActorDTO(1L, "Marlon Brando"));
        this.mockMvc
                .perform(get(ENDPOINT + "/10"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("actor"));
    }
}