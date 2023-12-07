package christmas.view;

import christmas.view.constant.input.RequestInputMessage;
import christmas.view.constant.output.ErrorMessage;

public class ErrorView {
    private static final String WHITE_SPACE = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private ErrorView() {
    }

    public static void printInvalidDate() {
        System.out.println(
                LINE_SEPARATOR +
                        ErrorMessage.INVALID_DATE.getMessage() +
                        WHITE_SPACE + RequestInputMessage.REQUEST_INPUT_AGAIN +
                        LINE_SEPARATOR
        );
    }

    public static void printInvalidOrder() {
        System.out.println(
                LINE_SEPARATOR +
                        ErrorMessage.INVALID_ORDER.getMessage() +
                        WHITE_SPACE + RequestInputMessage.REQUEST_INPUT_AGAIN +
                        LINE_SEPARATOR
        );
    }
}
