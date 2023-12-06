package christmas.view.constant.output;

public enum Format implements OutputMessage {
    MENU_AND_COUNT("%s %d개"),
    PAYMENT_AMOUNT("%,d원"),
    BENEFIT_AMOUNT("-%,d원"),
    BENEFIT_DETAIL("%s: " + BENEFIT_AMOUNT.message);

    private final String message;

    Format(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getMessageWith(Object... objects) {
        return String.format(message, objects);
    }
}
