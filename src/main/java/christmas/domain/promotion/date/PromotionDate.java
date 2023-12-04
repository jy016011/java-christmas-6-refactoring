package christmas.domain.promotion.date;

import christmas.domain.VisitingDate;

public interface PromotionDate {
    public boolean contains(VisitingDate visitingDate);
}
