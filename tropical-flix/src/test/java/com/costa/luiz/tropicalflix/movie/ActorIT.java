package com.costa.luiz.tropicalflix.movie;

import com.costa.luiz.tropicalflix.actor.Actor;
import com.costa.luiz.tropicalflix.actor.ActorRepositoryJPA;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("mysql")
@Testcontainers
@Disabled
class ActorIT {

    @Autowired
    ActorRepositoryJPA actorRepositoryJPA;

    @Container
    @ServiceConnection
    private static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0.32")
            .withDatabaseName("Tropicalflix")
            .withUsername("user")
            .withPassword("pass")
            .withInitScript("database/e2e_test.sql")
            .waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2));

    static {
        mySQLContainer.start();
//        Another way to load the script
//        var containerDelegate = new JdbcDatabaseDelegate(mySQLContainer, "");
//        ScriptUtils.runInitScript(containerDelegate, "database/e2e_data.sql");
    }

    @Test
    @DisplayName("Create a new actor")
    void createANewActor() {
        long count = actorRepositoryJPA.count();
        var actorName = "Alan Delon";
        var newActor = actorRepositoryJPA.save(Actor.ActorBuilder.anActor().withName(actorName).build());
        assertTrue(count < actorRepositoryJPA.count());
        assertThat(newActor).extracting("name").isEqualTo(actorName);
    }

    @AfterAll
    static void tearDown() {
        mySQLContainer.stop();
    }
}