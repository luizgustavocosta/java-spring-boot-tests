package com.costa.luiz.tropicalflix.actor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ActorController.class)
class ActorControllerTest {

    //Load Test Context

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorService actorService;

    @Test
    @DisplayName("Find all actors")
    void findAll() throws Exception {
        given(actorService.findAll(any(PageRequest.class))).willReturn(actors());

        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/actors").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualToIgnoringWhitespace("""
                [
                    {"id":10,"name":"Didi"},
                    {"id":20,"name":"Mussum"}
                ]
                """);
    }

    @Test
    @DisplayName("Find all when the actors list is empty")
    void findAllNoActors() throws Exception {
        given(actorService.findAll(any(PageRequest.class))).willReturn(Collections.emptyList());

        var response = mockMvc.perform(
                get("/api/v1/actors").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualToIgnoringWhitespace("""
                []
                """);
    }

    List<Actor> actors() {
        return List.of(
                Actor.ActorBuilder.anActor().withId(10L).withName("Didi").build(),
                Actor.ActorBuilder.anActor().withId(20L).withName("Mussum").build()
        );
    }
}