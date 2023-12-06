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
        orderDetails.forEach(OutputView::printMenuAndCount);
    }

    public static void printTotalOriginPrice(int totalOriginPrice) {
        System.out.println(HeadingOfResult.ORIGIN_TOTAL_PRICE.getMessage());
        System.out.println(Format.PAYMENT_AMOUNT.getMessageWith(totalOriginPrice));
    }

    public static void printGiftDetails(Map<String, Integer> giftDetails) {
        System.out.println(HeadingOfResult.GIFT_DETAIL.getMessage());
        giftDetails.forEach(OutputView::printMenuAndCount);
    }

    private static void printMenuAndCount(String menuName, int count) {
        System.out.println(Format.MENU_AND_COUNT.getMessageWith(menuName, count));
    }
}
