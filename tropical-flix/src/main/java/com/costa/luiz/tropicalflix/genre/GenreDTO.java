package com.costa.luiz.tropicalflix.genre;

public record GenreDTO(Long id, String name) {
    static Genre toGenre(GenreDTO genreDTO) {
        return Genre.GenreBuilder.aGenre()
                .withId(genreDTO.id)
                .withName(genreDTO.name)
                .build();
    }

    static GenreDTO toDTO(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }
}
