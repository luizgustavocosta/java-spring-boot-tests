package com.costa.luiz.tropicalflix.genre;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class GenreRepositoryJPATest {

    @Autowired
    GenreRepositoryJPA repositoryJPA;

    @Test
    @Sql("/database/createGenres.sql")
    void findAllByOrderByName() {
        var allByOrderByName = repositoryJPA.findAllByOrderByName(PageRequest.of(0, 5));
        assertThat(allByOrderByName)
                .as("Expecting the genre name be on asceding ordered - A to Z")
                .extracting("name")
                .contains("Action", "Comedy", "Terror");
    }
}