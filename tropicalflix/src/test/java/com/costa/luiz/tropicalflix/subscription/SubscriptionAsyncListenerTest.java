package com.costa.luiz.tropicalflix.subscription;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SubscriptionAsyncListenerTest {

    @InjectMocks
    private SubscriptionAsyncListener subscriptionAsyncListener;

    @Test
    void handle_shouldLogSubscriptionDetails_andNotifyUser() {
        // Arrange
        var startSubscription = new StartSubscription(
                "1234567890abcdefghij", "test@email.com", "10.0",
                BillingPeriod.MONTHLY, "Brasil", "Rede"
        );

        try (var logCaptor = LogCaptor.forClass(SubscriptionAsyncListener.class)) {

            // Act
            SubscriptionCreatedEvent event = new SubscriptionCreatedEvent(startSubscription, startSubscription);
            subscriptionAsyncListener.handle(event);

            // Assert
            assertThat(logCaptor.getLogs())
                    .hasSize(1)
                    .contains(
                            "Time to notify the user via e-mail [test@email.com]"
                    );
        }
    }
}