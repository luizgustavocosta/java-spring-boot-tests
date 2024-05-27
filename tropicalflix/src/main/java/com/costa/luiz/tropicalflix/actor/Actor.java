package com.costa.luiz.tropicalflix.actor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class ActorBuilder {
        private Long id;
        private String name;

        private ActorBuilder() {
        }

        public static ActorBuilder anActor() {
            return new ActorBuilder();
        }

        public ActorBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ActorBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Actor build() {
            Actor actor = new Actor();
            actor.id = this.id;
            actor.name = this.name;
            return actor;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
