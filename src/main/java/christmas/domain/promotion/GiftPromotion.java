package christmas.domain.promotion;

import static christmas.domain.promotion.condition.order.OriginTotalPrice.LIMIT_AMOUNT_OF_GIFT_PROMOTION;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.promotion.condition.date.PromotionPeriod;
import java.util.function.BiPredicate;

public enum GiftPromotion implements Promotion {
    CHAMPAGNE_GIFT(
            (visitingDate, order) ->
                    PromotionPeriod.DECEMBER.contains(visitingDate)
                            && LIMIT_AMOUNT_OF_GIFT_PROMOTION.isApplicablePrice(order),
            Gift.CHAMPAGNE
    );
    private final BiPredicate<VisitingDate, Order> condition;
    private final Gift gift;

    GiftPromotion(BiPredicate<VisitingDate, Order> condition, Gift gift) {
        this.condition = condition;
        this.gift = gift;
    }

    @Override
    public boolean isApplicable(VisitingDate visitingDate, Order order) {
        return condition.test(visitingDate, order);
    }

    public int getAmount() {
        return gift.calculatePrice();
    }

    public Gift getGift() {
        return gift;
    }
}
