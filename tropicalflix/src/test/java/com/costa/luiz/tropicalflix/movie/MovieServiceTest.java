package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.actor.Actor;
import com.costa.luiz.tropicalflix.genre.Genre;
import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    // Helped by Google AI Studio

    @Mock
    private MovieRepositoryJPA repository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void findById_shouldReturnMovieDTO_whenMovieExists() {
        // Arrange
        Long movieId = 1L;
        var movie = Movie.MovieBuilder.aMovie()
                .withId(movieId)
                .withTitle("Movie Title")
                .withReleasedYear(2023)
                .withMinutes(120)
                .withRating(4.5)
                .withOverview("Overview")
                .withDirector("Director")
                .withActors(Collections.emptySet())
                .withGenres(Collections.emptySet())
                .build();
        when(repository.findById(movieId)).thenReturn(Optional.of(movie));

        // Act
        var movieDTO = movieService.findById(movieId);

        // Assert
        assertEquals(movieId, movieDTO.id());
        assertEquals("Movie Title", movieDTO.title());
        assertNotNull(movieDTO.actors());
        assertNotNull(movieDTO.genres());
    }

    @Test
    void findById_shouldThrowNonExistingEntity_whenMovieDoesNotExist() {
        // Arrange
        Long movieId = 1L;
        when(repository.findById(movieId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NonExistingEntity.class, () -> movieService.findById(movieId));
    }

    @Test
    void findAll_shouldReturnListMovieDTO_whenMoviesExist() {
        // Arrange
        Pageable pageRequest = PageRequest.of(0, 10);
        var movies = new ArrayList<>();
        movies.add(Movie.MovieBuilder.aMovie()
                .withId(10L)
                .withTitle("The Simpsons")
                .withReleasedYear(2023)
                .withMinutes(120)
                .withRating(4.5)
                .withOverview("Good enough")
                .withDirector("Seymour Skinner")
                .withActors(Collections.emptySet())
                .withGenres(Collections.emptySet())
                .build());
        movies.add(Movie.MovieBuilder.aMovie()
                .withId(11L)
                .withTitle("Indiana Pacers x NYK")
                .withReleasedYear(2023)
                .withMinutes(120)
                .withRating(9.5)
                .withOverview("Outstanding")
                .withDirector("Spike Lee")
                .withActors(Collections.emptySet())
                .withGenres(Collections.emptySet()).build());
        when(repository.findAll(pageRequest)).thenReturn(new PageImpl(movies));

        // Act
        var movieDTOs = movieService.findAll(pageRequest);

        // Assert
        assertThat(movieDTOs).as("Checking the return")
                .extracting("id", "title", "rating", "director")
                .contains(
                        tuple(10L, "The Simpsons", 4.5, "Seymour Skinner"),
                        tuple(11L, "Indiana Pacers x NYK", 9.5, "Spike Lee"));
        assertEquals(2, movieDTOs.size());
    }

    @Test
    void findAllForUI_shouldReturnListMovieDTO_withPlainValues_whenMoviesExist() {
        // Arrange
        var movies = List.of(Movie.MovieBuilder.aMovie()
                        .withId(98L)
                        .withTitle("X-Men 1")
                        .withReleasedYear(2023)
                        .withMinutes(120)
                        .withRating(4.5)
                        .withOverview("Overview")
                        .withDirector("Director")
                        .withActors(Set.of(Actor.ActorBuilder.anActor()
                                .withId(4L)
                                .withName("Rogue")
                                .build()))
                        .withGenres(Set.of(Genre.GenreBuilder.aGenre()
                                .withId(4L)
                                .withName("Comic")
                                .build()))
                        .build(),
                Movie.MovieBuilder.aMovie()
                        .withId(99L)
                        .withTitle("X-Men 2 - The Xavier return")
                        .withReleasedYear(2023)
                        .withMinutes(120)
                        .withRating(4.5)
                        .withOverview("Overview")
                        .withDirector("Director")
                        .withActors(Set.of(
                                Actor.ActorBuilder.anActor().withId(11L).withName("Gambit").build(),
                                Actor.ActorBuilder.anActor().withId(12L).withName("Storm").build(),
                                Actor.ActorBuilder.anActor().withId(13L).withName("Xavier").build())
                        )
                        .withGenres(Set.of(
                                Genre.GenreBuilder.aGenre().withId(22L).withName("Comic").build(),
                                Genre.GenreBuilder.aGenre().withId(22L).withName("Adventure").build()))
                        .build());

        Pageable pageRequest = PageRequest.of(0, 10);
        when(repository.findAll(pageRequest)).thenReturn(new PageImpl<>(movies));

        // Act
        var movieDTOs = movieService.findAllForUI(pageRequest);

        // Assert
        assertEquals(2, movieDTOs.size());
        assertEquals(98L, movieDTOs.get(0).id());
        assertEquals("X-Men 1", movieDTOs.get(0).title());
        assertEquals("Rogue", movieDTOs.get(0).actorsAsString());
        assertEquals("Comic", movieDTOs.get(0).genresAsString());
        assertEquals(99L, movieDTOs.get(1).id());
        assertEquals("X-Men 2 - The Xavier return", movieDTOs.get(1).title());
        assertEquals("Gambit,Storm,Xavier", movieDTOs.get(1).actorsAsString());
        assertEquals("Adventure,Comic", movieDTOs.get(1).genresAsString());
    }

    @Test
    void count_shouldReturnMovieCount() {
        // Arrange
        long expectedCount = 10L;
        when(repository.count()).thenReturn(expectedCount);

        // Act
        long actualCount = movieService.count();

        // Assert
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void deleteById_shouldDeleteMovie() {
        // Arrange
        Long movieId = 1L;

        // Act
        movieService.deleteById(movieId);

        // Assert
        verify(repository).deleteById(movieId);
    }
}