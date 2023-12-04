package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.domain.VisitingDate;

public interface Promotion {
    public String getName();

    public boolean isApplicable(VisitingDate visitingDate, Order order);

}
