package costa.costa.luiz.tropicalflix.actor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
}
