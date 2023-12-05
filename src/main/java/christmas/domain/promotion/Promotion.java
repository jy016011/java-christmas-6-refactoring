package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.domain.VisitingDate;

public interface Promotion {
    String discountPromotionName = "할인 이벤트";
    String giftPromotionName = "증정 이벤트";

    boolean isApplicable(VisitingDate visitingDate, Order order);

}
