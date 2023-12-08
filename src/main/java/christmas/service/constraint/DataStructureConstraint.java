package christmas.service.constraint;

public enum DataStructureConstraint implements ParsingConstraint<Integer> {
    DIFFERENCE_BETWEEN_SEPARATOR_COUNTS(1),
    SIZE_OF_NAME_AND_COUNT_LIST(2),
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
