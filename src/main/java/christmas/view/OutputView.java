package christmas.view;

import christmas.domain.dto.AppliedDiscountsResponse;
import christmas.domain.dto.AppliedGiftsResponse;
import christmas.view.constant.output.Format;
import christmas.view.constant.output.HeadingOfResult;
import christmas.view.constant.output.OutputMessage;
import java.util.Map;

public class OutputView {
    private static final int ZERO = 0;

    private OutputView() {
    }

    public static void printGreeting() {
        System.out.println(OutputMessage.GREETING);
    }

    public static void printDateWithPreview(int visitDay) {
        System.out.println(OutputMessage.getDateWithPreview(visitDay));
    }

    public static void printOrderDetails(Map<String, Integer> orderDetails) {
        printLineSeparator();
        System.out.println(HeadingOfResult.ORDERS.getMessage());
        orderDetails.forEach(OutputView::printMenuAndCount);
    }

    public static void printTotalOriginPrice(int totalOriginPrice) {
        printLineSeparator();
        System.out.println(HeadingOfResult.ORIGIN_TOTAL_PRICE.getMessage());
        System.out.println(Format.PAYMENT_AMOUNT.getMessageWith(totalOriginPrice));
    }

    public static void printGiftDetails(AppliedGiftsResponse appliedGiftsResponse) {
        printLineSeparator();
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
        printLineSeparator();
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

    public static void printTotalBenefitAmount(
            AppliedDiscountsResponse appliedDiscountsResponse,
            AppliedGiftsResponse appliedGiftsResponse
    ) {
        printLineSeparator();
        System.out.println(HeadingOfResult.TOTAL_BENEFIT.getMessage());
        int totalBenefitAmount =
                appliedDiscountsResponse.totalBenefitAmount() + appliedGiftsResponse.totalBenefitAmount();
        if (totalBenefitAmount == ZERO) {
            System.out.println(OutputMessage.NO_BENEFIT_AMOUNT);
            return;
        }
        System.out.println(Format.BENEFIT_AMOUNT.getMessageWith(totalBenefitAmount));
    }

    public static void printExpectedPaymentAmount(int expectedPaymentAmount) {
        printLineSeparator();
        System.out.println(HeadingOfResult.EXPECTED_PAYMENT_AMOUNT.getMessage());
        System.out.println(Format.PAYMENT_AMOUNT.getMessageWith(expectedPaymentAmount));
    }

    public static void printPromotionBadge(String badgeName) {
        printLineSeparator();
        System.out.println(HeadingOfResult.BADGE.getMessage());
        System.out.println(badgeName);
    }

    private static void printMenuAndCount(String menuName, int count) {
        System.out.println(Format.MENU_AND_COUNT.getMessageWith(menuName, count));
    }

    private static void printAppliedDiscounts(AppliedDiscountsResponse appliedDiscountsResponse) {
        appliedDiscountsResponse.promotionNameAndBenefit()
                .forEach(OutputView::printEachPromotion);
    }

    private static void printAppliedGifts(AppliedGiftsResponse appliedGiftsResponse) {
        if (appliedGiftsResponse.totalBenefitAmount() == ZERO) {
            return;
        }
        printEachPromotion(appliedGiftsResponse.promotionName(), appliedGiftsResponse.totalBenefitAmount());
    }

    private static void printEachPromotion(String name, int amountOfBenefit) {
        System.out.println(Format.BENEFIT_DETAIL.getMessageWith(name, amountOfBenefit));
    }

    private static void printNothing() {
        System.out.println(OutputMessage.NO_BENEFIT);
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}
