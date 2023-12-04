package christmas.domain.constraint;

public enum DateConstraint implements PromotionConstraint {
    PROMOTION_YEAR(2023),
    PROMOTION_MONTH(12),
    DAY_OF_START(1),
    DAY_OF_LAST(31),
    CHRISTMAS(25);

    private final int value;

    DateConstraint(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
