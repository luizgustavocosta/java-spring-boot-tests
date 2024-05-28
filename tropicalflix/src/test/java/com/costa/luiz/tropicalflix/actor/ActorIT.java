package com.costa.luiz.tropicalflix.actor;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

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
    //@ServiceConnection
    static GenericContainer<?> mySQLContainer = new GenericContainer<>(DockerImageName.parse("16bits/tropicalflix_db:0.0.1"))
            .withExposedPorts(3306)
            .withEnv("MYSQL_DATABASE", "Tropicalflix")
            .withEnv("MYSQL_USER", "user")
            .withEnv("MYSQL_PASSWORD", "pass")
            .waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            ;

    static {
        mySQLContainer.start();
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:mysql://localhost:%d/Tropicalflix", mySQLContainer.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "user");
        registry.add("spring.datasource.password", () -> "pass");
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