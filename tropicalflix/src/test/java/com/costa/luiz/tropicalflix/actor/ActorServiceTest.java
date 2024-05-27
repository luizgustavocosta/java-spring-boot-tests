package com.costa.luiz.tropicalflix.actor;

import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActorServiceTest {

    // Helped by Google AI Studio

    @Mock
    private ActorRepositoryJPA repositoryJPA;

    @InjectMocks
    private ActorService actorService;

    @Test
    void findAll() {
        // Arrange
        Pageable pageRequest = PageRequest.of(0, 10);
        Actor actor1 = Actor.ActorBuilder.anActor().withId(1L).withName("Actor 1").build();
        Actor actor2 = Actor.ActorBuilder.anActor().withId(2L).withName("Actor 2").build();
        List<Actor> actors = List.of(actor1, actor2);
        Page<Actor> actorPage = new PageImpl<>(actors, pageRequest, actors.size());
        when(repositoryJPA.findAll(pageRequest)).thenReturn(actorPage);

        // Act
        List<ActorDTO> result = actorService.findAll(pageRequest);

        // Assert
        assertThat(result).hasSize(2);
        verify(repositoryJPA).findAll(pageRequest);
    }

    @Test
    void count() {
        // Arrange
        long expectedCount = 10;
        when(repositoryJPA.count()).thenReturn(expectedCount);

        // Act
        long actualCount = actorService.count();

        // Assert
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(repositoryJPA).count();
    }

    @Test
    void findById_existingActor() {
        // Arrange
        Long id = 1L;
        Actor actor = Actor.ActorBuilder.anActor().withId(id).withName("Actor 1").build();
        when(repositoryJPA.findById(id)).thenReturn(Optional.of(actor));

        // Act
        ActorDTO result = actorService.findById(id);

        // Assert
        assertAll("Grouped assertions",
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.id()).isEqualTo(id),
                () -> assertThat(result.name()).isEqualTo("Actor 1"));
        verify(repositoryJPA).findById(id);
    }

    @Test
    void findById_nonExistingActor() {
        // Arrange
        Long id = 1L;
        when(repositoryJPA.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> actorService.findById(id))
                .isInstanceOf(NonExistingEntity.class);
        verify(repositoryJPA).findById(id);
    }

    @Test
    void deleteById() {
        // Arrange
        Long id = 1L;

        // Act
        actorService.deleteById(id);

        // Assert
        verify(repositoryJPA).deleteById(id);
    }

    @Test
    void save() {
        // Arrange
        ActorDTO actorDTO = new ActorDTO(1L, "Actor 1");
        Actor expectedActor = Actor.ActorBuilder.anActor().withId(1L).withName("Actor 1").build();
        when(repositoryJPA.save(any(Actor.class))).thenReturn(expectedActor);

        // Act
        actorService.save(actorDTO);

        // Assert
        verify(repositoryJPA).save(expectedActor);
    }
}