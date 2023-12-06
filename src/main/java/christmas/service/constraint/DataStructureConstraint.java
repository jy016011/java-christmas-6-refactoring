package christmas.service.constraint;

public enum DataStructureConstraint implements ParsingConstraint<Integer> {
    DIFFERENCE_BETWEEN_SEPARATORS(1),
    LIST_OF_NAME_AND_COUNT_SIZE(2),
    NAME_INDEX(0),
    COUNT_INDEX(1);

    private final Integer value;

    DataStructureConstraint(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
