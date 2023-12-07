package christmas.view;

import christmas.domain.dto.AppliedDiscountsResponse;
import christmas.domain.dto.AppliedGiftsResponse;
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

    public static void printGiftDetails(AppliedGiftsResponse appliedGiftsResponse) {
        System.out.println(HeadingOfResult.GIFT_DETAIL.getMessage());
        if (appliedGiftsResponse.giftNameAndQuantity().isEmpty()) {
            printNothing();
            return;
        }
        appliedGiftsResponse.giftNameAndQuantity().forEach(OutputView::printMenuAndCount);
    }

    public static void printPromotionDetails(
            AppliedDiscountsResponse appliedDiscountsResponse,
            AppliedGiftsResponse appliedGiftsResponse
    ) {
        System.out.println(HeadingOfResult.BENEFIT_DETAIL.getMessage());
        if (
                appliedDiscountsResponse.promotionNameAndBenefit().isEmpty() &&
                        appliedGiftsResponse.giftNameAndQuantity().isEmpty()
        ) {
            printNothing();
            return;
        }
        printAppliedDiscounts(appliedDiscountsResponse);
        printAppliedGifts(appliedGiftsResponse);
    }

    private static void printMenuAndCount(String menuName, int count) {
        System.out.println(Format.MENU_AND_COUNT.getMessageWith(menuName, count));
    }

    private static void printAppliedDiscounts(AppliedDiscountsResponse appliedDiscountsResponse) {
        appliedDiscountsResponse.promotionNameAndBenefit()
                .forEach(OutputView::printEachPromotion);
    }

    private static void printAppliedGifts(AppliedGiftsResponse appliedGiftsResponse) {
        printEachPromotion(appliedGiftsResponse.promotionName(), appliedGiftsResponse.totalBenefitAmount());
    }

    private static void printEachPromotion(String name, int amountOfBenefit) {
        System.out.println(Format.BENEFIT_DETAIL.getMessageWith(name, amountOfBenefit));
    }

    private static void printNothing() {
        System.out.println(OutputMessage.NO_BENEFIT);
    }
}
