package com.costa.luiz.tropicalflix.subscription;

import org.springframework.context.ApplicationEvent;

class CreateRecommendationEvent extends ApplicationEvent {

    final Recommendation recommendation;
    public CreateRecommendationEvent(Object source, Recommendation recommendation) {
        super(source);
        this.recommendation = recommendation;
    }
}
