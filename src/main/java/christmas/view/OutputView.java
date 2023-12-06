package christmas.view;

import christmas.view.constant.output.Format;
import christmas.view.constant.output.HeadingOfResult;
import christmas.view.constant.output.OutputMessage;
import java.util.Map;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printGreeting() {
        System.out.println(OutputMessage.GREETING);
    }

    public static void printOrderDetails(Map<String, Integer> orderDetails) {
        System.out.println(HeadingOfResult.ORDERS.getMessage());
        orderDetails.forEach(
                (menuName, count) -> System.out.println(Format.MENU_AND_COUNT.getMessageWith(menuName, count))
        );
    }

}
