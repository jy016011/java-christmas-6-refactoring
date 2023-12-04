package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.menu.Menu;
import java.util.function.BiPredicate;

public enum GiftPromotion implements Promotion {
//    CHAMPAGNE_GIFT(
//            "증정 이벤트",
//            (visitingDate, order) ->
//                    PromotionPeriod.DECEMBER.contains(visitingDate)
//                            && order.getOriginTotalPrice() >= LIMIT_PRICE_OF_GIFT_PROMOTION,
//            CHAMPAGNE,
//            1
//    )
//    ;
    ;
    private final String name;
    private final BiPredicate<VisitingDate, Order> condition;
    private final Menu gift;
    private final int quantity;

    GiftPromotion(String name, BiPredicate<VisitingDate, Order> condition, Menu gift, int quantity) {
        this.name = name;
        this.condition = condition;
        this.gift = gift;
        this.quantity = quantity;
    }

    @Override
    public boolean isApplicable(VisitingDate visitingDate, Order order) {
        return condition.test(visitingDate, order);
    }

    public int getAmount() {
        return gift.getPrice() * quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    public Menu getGift() {
        return gift;
    }

    public int getQuantity() {
        return quantity;
    }
}
