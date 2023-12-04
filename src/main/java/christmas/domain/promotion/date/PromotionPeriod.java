package christmas.domain.promotion.date;

import static christmas.domain.constraint.DateConstraint.CHRISTMAS;
import static christmas.domain.constraint.DateConstraint.DAY_OF_LAST;
import static christmas.domain.constraint.DateConstraint.DAY_OF_START;
import static christmas.domain.constraint.DateConstraint.PROMOTION_MONTH;
import static christmas.domain.constraint.DateConstraint.PROMOTION_YEAR;

import christmas.domain.VisitingDate;
import java.time.LocalDate;

public enum PromotionPeriod implements PromotionDate {
    DECEMBER(
            LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), DAY_OF_START.getValue()),
            LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), DAY_OF_LAST.getValue())
    ),
    UNTIL_CHRISTMAS(
            LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), DAY_OF_START.getValue()),
            LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), CHRISTMAS.getValue())
    );

    private final LocalDate startDateInclusive;
    private final LocalDate lastDateInclusive;

    PromotionPeriod(LocalDate startDateInclusive, LocalDate lastDateInclusive) {
        this.startDateInclusive = startDateInclusive;
        this.lastDateInclusive = lastDateInclusive;
    }

    @Override
    public boolean contains(VisitingDate visitingDate) {
        return visitingDate.isContainedIn(startDateInclusive, lastDateInclusive);
    }
}
