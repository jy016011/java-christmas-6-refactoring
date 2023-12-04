package christmas.domain.promotion.condition.date;

import christmas.domain.VisitingDate;

public interface PromotionDate {
    public boolean contains(VisitingDate visitingDate);
}
