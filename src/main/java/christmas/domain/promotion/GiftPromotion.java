package christmas.domain.promotion;

import static christmas.domain.promotion.condition.order.OriginTotalPrice.LIMIT_AMOUNT_OF_GIFT_PROMOTION;
import static christmas.domain.promotion.condition.order.OriginTotalPrice.MIN_AMOUNT_FOR_APPLICABLE;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.promotion.condition.date.PromotionPeriod;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public enum GiftPromotion implements Promotion {
    CHAMPAGNE_GIFT(
            PromotionPeriod.DECEMBER::contains,
            (visitingDate, order) -> LIMIT_AMOUNT_OF_GIFT_PROMOTION.isApplicablePrice(order),
            Gift.CHAMPAGNE
    );

    private final Predicate<VisitingDate> period;
    private final BiPredicate<VisitingDate, Order> condition;
    private final Gift gift;

    GiftPromotion(
            Predicate<VisitingDate> period,
            BiPredicate<VisitingDate, Order> condition,
            Gift gift
    ) {
        this.period = period;
        this.condition = condition;
        this.gift = gift;
    }

    @Override
    public boolean isApplicable(VisitingDate visitingDate, Order order) {
        return period.test(visitingDate)
                && condition.test(visitingDate, order)
                && MIN_AMOUNT_FOR_APPLICABLE.isApplicablePrice(order);
    }

    public int getAmount() {
        return gift.calculatePrice();
    }

    public Gift getGift() {
        return gift;
    }
}
