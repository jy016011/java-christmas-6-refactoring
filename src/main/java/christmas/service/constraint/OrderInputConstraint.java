package christmas.service.constraint;

public enum OrderInputConstraint implements ParsingConstraint<String> {
    MENU_SEPARATOR(","),
    NAME_AND_COUNT_SEPARATOR("-");

    private final String value;

    OrderInputConstraint(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
