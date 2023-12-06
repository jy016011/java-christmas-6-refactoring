package christmas.view.constant.input;

public enum InputForm implements RequestInputMessage {
    ONLY_NUMBER("(숫자만 입력해 주세요!)"),
    ORDER_EXAMPLE("(e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    private final String message;

    InputForm(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
