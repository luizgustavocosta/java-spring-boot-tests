package com.costa.luiz.tropicalflix.subscription;

import org.joda.time.Period;

import java.util.Arrays;

enum BillingPeriod {

    DAILY(Period.days(1)),
    WEEKLY(Period.weeks(1)),
    BIWEEKLY(Period.weeks(2)),
    THIRTY_DAYS(Period.days(30)),
    THIRTY_ONE_DAYS(Period.days(31)),
    SIXTY_DAYS(Period.days(60)),
    NINETY_DAYS(Period.days(90)),
    MONTHLY(Period.months(1)),
    BIMESTRIAL(Period.months(2)),
    QUARTERLY(Period.months(3)),
    TRIANNUAL(Period.months(4)),
    BIANNUAL(Period.months(6)),
    ANNUAL(Period.years(1)),
    SESQUIENNIAL(Period.months(18)),
    BIENNIAL(Period.years(2)),
    TRIENNIAL(Period.years(3)),
    NO_BILLING_PERIOD(Period.ZERO);

    private final Period period;

    BillingPeriod(final Period period) {
        this.period = period;
    }

    public static BillingPeriod of(String periodInDays) {
        Period period = Period.days(Integer.parseInt(periodInDays));
        return Arrays.stream(BillingPeriod.values())
                .filter(current -> current.getPeriod().equals(period))
                .findFirst().orElse(BillingPeriod.MONTHLY);
    }

    public Period getPeriod() {
        return period;
    }
}
