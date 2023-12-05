package christmas.domain.promotion.condition.date;

import christmas.domain.VisitingDate;

public interface PromotionDate {
    boolean contains(VisitingDate visitingDate);
}
