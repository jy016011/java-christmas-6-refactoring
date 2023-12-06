package christmas.view.constant.output;

public enum ResultFormat implements OutputMessage {
    MENU_AND_COUNT("%s %d개"),
    PAYMENT_AMOUNT("%,d원"),
    BENEFIT_AMOUNT("-%,d원"),
    BENEFIT_DETAIL("%s: " + BENEFIT_AMOUNT.message),
    NO_BENEFIT("없음"),
    NO_BENEFIT_AMOUNT("0원");

    private final String message;

    ResultFormat(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
