package com.costa.luiz.tropicalflix.subscription;

import com.costa.luiz.tropicalflix.subscription.external.recomendation.CreateRecommendationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SubscriptionPublisherTest {
    // Helped by Google AI Studio
    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private SubscriptionPublisher subscriptionPublisher;

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"Brasil", "USA", "SPAIN", "MADAGASCAR"})
    void publish_shouldPublishSubscriptionCreatedEvent_andCreateRecommendationEvent(String country) {
        // Arrange
        StartSubscriptionRequest subscriptionRequest = new StartSubscriptionRequest(
                "test@email.com", "10.0", "90", country
        );

        // Act
        subscriptionPublisher.publish(subscriptionRequest);

        // Assert
        verify(publisher).publishEvent(argThat(event -> event instanceof SubscriptionCreatedEvent));
        verify(publisher).publishEvent(argThat(event -> event instanceof CreateRecommendationEvent));

        // Verify the content of the events
//        verify(publisher).publishEvent(
//                argThat(event -> {
//                    if (event instanceof SubscriptionCreatedEvent createdEvent) {
//                        StartSubscription startSubscription = createdEvent.getStartSubscription();
//                        return startSubscription.email().equals("test@email.com")
//                                && startSubscription.billingAmount().equals("10.0")
//                                && startSubscription.billingPeriod() == BillingPeriod.MONTHLY
//                                && startSubscription.country().equals("Brasil")
//                                && startSubscription.financialProcessor().equals("Rede"); // Brasil uses "Rede"
//                    }
//                    return false;
//                }));
//
//        verify(publisher, times(1)).publishEvent(
//                argThat(event -> {
//                    if (event instanceof CreateRecommendationEvent recommendationEvent) {
//                        return recommendationEvent.getRecommendation() != null;
//                    }
//                    return false;
//                }));
    }
}