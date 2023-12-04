package christmas.domain.constraint;

public enum OrderConstraint implements PromotionConstraint {
    MIN_COUNT_OF_EACH_MENU(1),
    MAX_COUNT_OF_TOTAL_MENU(20);
    private final int value;

    OrderConstraint(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
