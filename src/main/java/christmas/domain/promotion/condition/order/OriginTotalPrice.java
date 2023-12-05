package christmas.domain.promotion.condition.order;

import christmas.domain.Order;
import christmas.domain.constraint.OrderConstraint;

public enum OriginTotalPrice implements PromotionOrder {
    MIN_AMOUNT_FOR_APPLICABLE(OrderConstraint.MIN_PRICE_OF_APPLICABLE.getValue()),
    LIMIT_AMOUNT_OF_GIFT_PROMOTION(OrderConstraint.LIMIT_PRICE_OF_GIFT_PROMOTION.getValue());

    private final int conditionAmount;

    OriginTotalPrice(int conditionAmount) {
        this.conditionAmount = conditionAmount;
    }

    public boolean isApplicablePrice(Order order) {
        return order.isOriginTotalPriceNoLessThan(conditionAmount);
    }
}
