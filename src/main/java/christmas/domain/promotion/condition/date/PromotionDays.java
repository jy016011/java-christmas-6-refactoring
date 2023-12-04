package christmas.domain.promotion.condition.date;

import static christmas.domain.constraint.DateConstraint.PROMOTION_MONTH;
import static christmas.domain.constraint.DateConstraint.PROMOTION_YEAR;

import christmas.domain.VisitingDate;
import java.time.LocalDate;
import java.util.List;

public enum PromotionDays implements PromotionDate {
    SPECIAL_DAYS(
            List.of(
                    LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), 3),
                    LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), 10),
                    LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), 17),
                    LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), 24),
                    LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), 25),
                    LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), 31)
            )
    );

    private List<LocalDate> days;

    PromotionDays(List<LocalDate> days) {
        this.days = days;
    }

    @Override
    public boolean contains(VisitingDate visitingDate) {
        return visitingDate.isContainedIn(days);
    }
}
