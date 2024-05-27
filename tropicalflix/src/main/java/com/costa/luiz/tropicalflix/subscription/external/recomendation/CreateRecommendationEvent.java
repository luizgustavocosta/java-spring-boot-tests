package com.costa.luiz.tropicalflix.subscription.external.recomendation;

import org.springframework.context.ApplicationEvent;

public class CreateRecommendationEvent extends ApplicationEvent {

    final Recommendation recommendation;
    public CreateRecommendationEvent(Object source, Recommendation recommendation) {
        super(source);
        this.recommendation = recommendation;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }
}
