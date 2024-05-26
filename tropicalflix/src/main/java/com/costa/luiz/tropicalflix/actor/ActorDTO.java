package com.costa.luiz.tropicalflix.actor;

record ActorDTO(Long id, String name) {
    //FIXME - Don't call the entity here
    static ActorDTO toDTO(Actor actor) {
        return new ActorDTO(actor.getId(), actor.getName());
    }
}
