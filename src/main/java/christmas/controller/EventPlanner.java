package christmas.controller;

import christmas.domain.AppliedDiscounts;
import christmas.domain.AppliedGifts;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.badge.Badge;
import christmas.domain.constraint.DateConstraint;
import christmas.domain.dto.AppliedDiscountsResponse;
import christmas.domain.dto.AppliedGiftsResponse;
import christmas.domain.menu.Menu;
import christmas.service.BenefitService;
import christmas.service.ParseService;
import christmas.view.ErrorView;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class EventPlanner {
    private EventPlanner() {
    }

    public static void run() {
        OutputView.printGreeting();
        VisitingDate visitingDate = getVisitingDate();
        Order order = getOrder();
        printUserInputs(visitingDate, order);
        applyPromotions(visitingDate, order);
    }

    private static VisitingDate getVisitingDate() {
        while (true) {
            try {
                String userInput = InputView.requestVisitDayInput();
                int visitDay = ParseService.toNumber(userInput);
                return new VisitingDate(visitDay);
            } catch (IllegalArgumentException e) {
                ErrorView.printInvalidDate();
            }
        }
    }

    private static Order getOrder() {
        while (true) {
            try {
                String userInput = InputView.requestOrder();
                Map<Menu, Integer> orderInput = ParseService.toOrderMap(userInput);
                return new Order(orderInput);
            } catch (IllegalArgumentException e) {
                ErrorView.printInvalidOrder();
            }
        }
    }

    private static void printUserInputs(VisitingDate visitingDate, Order order) {
        OutputView.printDateWithPreview(
                visitingDate.getDifferenceFromStartDay() + DateConstraint.DAY_OF_START.getValue()
        );

        OutputView.printOrderDetails(order.getOrderDetails());
        OutputView.printTotalOriginPrice(order.calculateTotalOriginPrice());
    }

    private static void applyPromotions(VisitingDate visitingDate, Order order) {
        AppliedDiscounts appliedDiscounts = getAppliedDiscounts(visitingDate, order);
        AppliedGifts appliedGifts = getAppliedGifts(visitingDate, order);
        printAppliedPromotions(appliedDiscounts, appliedGifts);
        printExpectedPaymentAmount(order, appliedDiscounts);
        printBadge(appliedDiscounts, appliedGifts);
    }

    private static AppliedDiscounts getAppliedDiscounts(VisitingDate visitingDate, Order order) {
        return BenefitService.getApplicableDiscounts(visitingDate, order);
    }

    private static AppliedGifts getAppliedGifts(VisitingDate visitingDate, Order order) {
        return BenefitService.getApplicableGifts(visitingDate, order);
    }

    private static void printAppliedPromotions(AppliedDiscounts appliedDiscounts, AppliedGifts appliedGifts) {
        AppliedDiscountsResponse appliedDiscountsResponse = new AppliedDiscountsResponse(appliedDiscounts);
        AppliedGiftsResponse appliedGiftsResponse = new AppliedGiftsResponse(appliedGifts);
        printPromotionResults(appliedDiscountsResponse, appliedGiftsResponse);
    }

    private static void printPromotionResults(
            AppliedDiscountsResponse appliedDiscountsResponse,
            AppliedGiftsResponse appliedGiftsResponse
    ) {
        OutputView.printGiftDetails(appliedGiftsResponse);
        OutputView.printPromotionDetails(appliedDiscountsResponse, appliedGiftsResponse);
        OutputView.printTotalBenefitAmount(appliedDiscountsResponse, appliedGiftsResponse);
    }

    private static void printExpectedPaymentAmount(Order order, AppliedDiscounts appliedDiscounts) {
        int expectedPaymentAmount = order.calculateTotalOriginPrice() - appliedDiscounts.getTotalBenefitAmount();
        OutputView.printExpectedPaymentAmount(expectedPaymentAmount);
    }

    private static void printBadge(AppliedDiscounts appliedDiscounts, AppliedGifts appliedGifts) {
        int totalBenefitAmount = appliedDiscounts.getTotalBenefitAmount() + appliedGifts.getTotalBenefitAmount();
        Badge badge = BenefitService.getBadgeBy(totalBenefitAmount);
        OutputView.printPromotionBadge(badge.getName());
    }
}
