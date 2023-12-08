package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.domain.VisitingDate;

public interface Promotion {
    String DISCOUNT_PROMOTION_NAME = "할인 이벤트";
    String GIFT_PROMOTION_NAME = "증정 이벤트";

    boolean isApplicable(VisitingDate visitingDate, Order order);

}
