package com.costa.luiz.tropicalflix.actor;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
class ActorRepositoryJPATest {

    @Autowired
    ActorRepositoryJPA repositoryJPA;

    @Test
    @DisplayName("Find all actors ordering by ascending name")
    @DatabaseSetup("/database/createActors.xml")
    void findAllByOrderByNameAsc() {
        var actors = repositoryJPA.findAllByOrderByNameAsc(PageRequest.of(0, 10));
        assertAll(
                () -> assertThat(actors).hasSize(2),
                () -> assertThat(actors)
                        .extracting("name")
                        .contains("Keanu Reeves", "Wesley Snipes")
        );
    }
}