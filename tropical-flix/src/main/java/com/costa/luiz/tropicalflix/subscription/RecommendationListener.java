package com.costa.luiz.tropicalflix.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class RecommendationListener implements ApplicationListener<CreateRecommendationEvent> {

    private final Logger logger = LoggerFactory.getLogger(RecommendationListener.class);

    @Override
    public void onApplicationEvent(CreateRecommendationEvent event) {
        logger.info("{}", Thread.currentThread());
        logger.info("{}", event.recommendation);
    }
}
