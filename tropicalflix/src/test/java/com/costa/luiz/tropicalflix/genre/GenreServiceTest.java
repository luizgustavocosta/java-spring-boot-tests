package com.costa.luiz.tropicalflix.genre;

import com.costa.luiz.tropicalflix.shared.NonExistingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepositoryJPA repositoryJPA;

    @InjectMocks
    private GenreService genreService;

    @Test
    void findAll_shouldReturnListGenreDTO_whenGenresExist() {
        // Arrange

        var genres = List.of(Genre.GenreBuilder.aGenre()
                        .withId(50L).withName("Action").build(),
                Genre.GenreBuilder.aGenre()
                        .withId(51L).withName("Terror").build());

        Pageable pageable = PageRequest.of(0, 10);
        when(repositoryJPA.findAll(pageable)).thenReturn(new PageImpl<>(genres));

        // Act
        List<GenreDTO> genreDTOs = genreService.findAll(pageable);

        // Assert
        assertEquals(2, genreDTOs.size());
        assertEquals(50L, genreDTOs.get(0).id());
        assertEquals("Action", genreDTOs.get(0).name());
        assertEquals(51L, genreDTOs.get(1).id());
        assertEquals("Terror", genreDTOs.get(1).name());
    }

    @Test
    void count_shouldReturnGenreCount() {
        // Arrange
        long expectedCount = 10L;
        when(repositoryJPA.count()).thenReturn(expectedCount);

        // Act
        long actualCount = genreService.count();

        // Assert
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void save_shouldSaveNewGenre() {
        // Arrange
        GenreDTO genreDTO = new GenreDTO(null, "Sci-Fi");
        Genre genreToSave = Genre.GenreBuilder.aGenre().withName("Sci-Fi").build();
        when(repositoryJPA.save(genreToSave)).thenReturn(Genre.GenreBuilder.aGenre()
                .withId(401L).withName("Sci-Fi").build());

        // Act
        GenreDTO savedGenreDTO = genreService.save(genreDTO);

        // Assert
        assertEquals(401L, savedGenreDTO.id());
        assertEquals("Sci-Fi", savedGenreDTO.name());
        // The times(nth) could be avoided
        verify(repositoryJPA, times(1)).save(genreToSave);
    }

    @Test
    void findById_shouldReturnGenreDTO_whenGenreExists() {
        // Arrange
        long genreId = 98L;
        var genre = Genre.GenreBuilder.aGenre().withId(98L).withName("Animation").build();
        when(repositoryJPA.findById(genreId)).thenReturn(Optional.of(genre));

        // Act
        GenreDTO genreDTO = genreService.findById(genreId);

        // Assert
        assertEquals(genreId, genreDTO.id());
        assertEquals("Animation", genreDTO.name());
    }

    @Test
    void findById_shouldThrowNonExistingEntity_whenGenreDoesNotExist() {
        // Arrange
        long genreId = 1L;
        when(repositoryJPA.findById(genreId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NonExistingEntity.class, () -> genreService.findById(genreId));
    }

    @Test
    void deleteById_shouldDeleteGenre() {
        // Arrange
        long genreId = 1L;

        // Act
        genreService.deleteById(genreId);

        // Assert
        verify(repositoryJPA).deleteById(genreId);
    }
}