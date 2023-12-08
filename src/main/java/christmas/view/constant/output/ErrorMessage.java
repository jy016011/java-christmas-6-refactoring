package christmas.view.constant.output;

public enum ErrorMessage implements OutputMessage {
    INVALID_DATE(ERROR_HEADER + " 유효하지 않은 날짜입니다."),
    INVALID_ORDER(ERROR_HEADER + " 유효하지 않은 주문입니다."),
    ONLY_DRINK(ERROR_HEADER + " 음료만 주문할 수 없습니다."),
    NOT_IN_MENU(ERROR_HEADER + " 메뉴에 없는 주문입니다."),
    INVALID_BENEFIT_AMOUNT(ERROR_HEADER + " 유효하지 않은 총 혜택 금액입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
