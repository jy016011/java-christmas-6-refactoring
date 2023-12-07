package christmas.view.constant.output;

public enum ErrorMessage implements OutputMessage {
    INVALID_DATE(ERROR_HEADER + " 유효하지 않은 날짜입니다."),
    INVALID_ORDER(ERROR_HEADER + " 유효하지 않은 주문입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
