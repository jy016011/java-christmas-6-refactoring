package christmas.domain.constraint;

public enum DateConstraint implements PromotionConstraint {
    YEAR(2023),
    MONTH(12),
    DAY_OF_START(1),
    DAY_OF_LAST(31);

    private final int value;

    DateConstraint(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
