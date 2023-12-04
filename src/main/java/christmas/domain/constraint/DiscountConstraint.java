package christmas.domain.constraint;

public enum DiscountConstraint implements PromotionConstraint {
    CHRISTMAS_D_DAY_BASE_AMOUNT(1_000),
    CHRISTMAS_D_DAY_ADDITIONAL_UNIT_AMOUNT(100),
    DAY_OF_WEEK_UNIT_AMOUNT(2_023),
    SPECIAL_BASE_AMOUNT(1_000);
    private final int value;

    DiscountConstraint(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
