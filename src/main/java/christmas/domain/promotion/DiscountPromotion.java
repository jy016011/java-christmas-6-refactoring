package christmas.domain.promotion;

import static christmas.domain.constraint.DiscountConstraint.CHRISTMAS_D_DAY_ADDITIONAL_UNIT_AMOUNT;
import static christmas.domain.constraint.DiscountConstraint.CHRISTMAS_D_DAY_BASE_AMOUNT;
import static christmas.domain.constraint.DiscountConstraint.DAY_OF_WEEK_UNIT_AMOUNT;
import static christmas.domain.constraint.DiscountConstraint.SPECIAL_BASE_AMOUNT;
import static christmas.domain.promotion.condition.date.PromotionDays.SPECIAL_DAYS;
import static christmas.domain.promotion.condition.date.PromotionPeriod.DECEMBER;
import static christmas.domain.promotion.condition.date.PromotionPeriod.UNTIL_CHRISTMAS;
import static christmas.domain.promotion.condition.order.OriginTotalPrice.MIN_AMOUNT_FOR_APPLICABLE;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum DiscountPromotion implements Promotion {
    CHRISTMAS_D_DAY(
            "크리스마스 디데이 할인",
            ((visitingDate, order) -> UNTIL_CHRISTMAS.contains(visitingDate)
                    && MIN_AMOUNT_FOR_APPLICABLE.isApplicablePrice(order)),
            (visitingDate, order) -> CHRISTMAS_D_DAY_BASE_AMOUNT.getValue()
                    + visitingDate.getDifferenceFromStartDay * CHRISTMAS_D_DAY_ADDITIONAL_UNIT_AMOUNT.getValue()
    ),
    WEEKDAY(
            "평일 할인",
            (visitingDate, order) -> DECEMBER.contains(visitingDate)
                    && MIN_AMOUNT_FOR_APPLICABLE.isApplicablePrice(order),
            (visitingDate, order) -> DAY_OF_WEEK_UNIT_AMOUNT.getValue() * order.getCountOfDessert()
    ),
    WEEKEND(
            "주말 할인",
            (visitingDate, order) -> DECEMBER.contains(visitingDate)
                    && MIN_AMOUNT_FOR_APPLICABLE.isApplicablePrice(order),
            (visitingDate, order) -> DAY_OF_WEEK_UNIT_AMOUNT.getValue() * order.getCountOfMain()
    ),
    SPECIAL_DAY(
            "특별 할인",
            (visitingDate, order) -> SPECIAL_DAYS.contains(visitingDate)
                    && MIN_AMOUNT_FOR_APPLICABLE.isApplicablePrice(order),
            (visitingDate, order) -> SPECIAL_BASE_AMOUNT.getValue()
    );


    private final String name;
    private final BiPredicate<VisitingDate, Order> condition;
    private final BiFunction<VisitingDate, Order, Integer> amountCalculator;

    DiscountPromotion(
            String name,
            BiPredicate<VisitingDate, Order> condition,
            BiFunction<VisitingDate, Order, Integer> amountCalculator
    ) {
        this.name = name;
        this.condition = condition;
        this.amountCalculator = amountCalculator;
    }

    @Override
    public boolean isApplicable(VisitingDate visitingDate, Order order) {
        return condition.test(visitingDate, order);
    }

    public int getAmount(VisitingDate visitingDate, Order order) {
        return amountCalculator.apply(visitingDate, order);
    }

    @Override
    public String getName() {
        return name;
    }
}
