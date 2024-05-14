package costa.costa.luiz.tropicalflix.subscription;

public record StartSubscription(String uuid, String email,
                                String billingAmount, String billingPeriod,
                                String country, String financialProcessor) {
}
