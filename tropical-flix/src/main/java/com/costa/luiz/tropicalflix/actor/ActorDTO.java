package com.costa.luiz.tropicalflix.actor;

record ActorDTO(Long id, String name) {

    static Actor toActor(Long id, String name) {
        return Actor.ActorBuilder.anActor()
                .withId(id)
                .withName(name)
                .build();
    }

    static ActorDTO toDTO(Actor actor) {
        return new ActorDTO(actor.getId(), actor.getName());
    }
}
