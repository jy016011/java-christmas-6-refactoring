package christmas.domain.constraint;

public enum OrderConstraint implements PromotionConstraint {
    MIN_COUNT_OF_EACH_MENU(1),
    MAX_COUNT_OF_TOTAL_MENU(20),
    MIN_PRICE_OF_APPLICABLE(10_000),
    LIMIT_PRICE_OF_GIFT_PROMOTION(120_000);
    private final int value;

    OrderConstraint(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
