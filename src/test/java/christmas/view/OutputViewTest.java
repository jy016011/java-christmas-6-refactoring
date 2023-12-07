package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.AppliedDiscounts;
import christmas.domain.AppliedGifts;
import christmas.domain.dto.AppliedDiscountsResponse;
import christmas.domain.dto.AppliedGiftsResponse;
import christmas.domain.promotion.DiscountPromotion;
import christmas.domain.promotion.Gift;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    private PrintStream standardOut;
    private ByteArrayOutputStream captor;

    @BeforeEach
    public void init() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    public void reset() {
        System.setOut(standardOut);
        captor.reset();
    }

    private String output() {
        return captor.toString();
    }

    @DisplayName("인사 문구 출력")
    @Test
    void printGreeting() {
        OutputView.printGreeting();
        assertThat(output()).contains("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @DisplayName("주문 메뉴 출력")
    @Test
    void printOrderDetails() {
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("타파스", 1);
        order.put("티본스테이크", 1);
        order.put("레드와인", 1);
        OutputView.printOrderDetails(order);
        assertThat(output()).contains(
                "<주문 메뉴>",
                "타파스 1개",
                "티본스테이크 1개",
                "레드와인 1개"
        );
    }

    @DisplayName("할인 전 총주문 금액 출력")
    @Test
    void printTotalOriginPrice() {
        int totalOriginPrice = 142_000;
        OutputView.printTotalOriginPrice(totalOriginPrice);
        assertThat(output()).contains(
                "<할인 전 총주문 금액>",
                "142,000원"
        );
    }

    @DisplayName("증정 메뉴 출력")
    @Test
    void printGiftDetails() {
        AppliedGiftsResponse appliedGiftsResponse = getAppliedGiftsResponse();
        OutputView.printGiftDetails(appliedGiftsResponse);
        assertThat(output()).contains(
                "<증정 메뉴>",
                "샴페인 1개"
        );
    }

    @DisplayName("증정 메뉴 없음 출력")
    @Test
    void printNoneGift() {
        AppliedGiftsResponse appliedGiftsResponse =
                new AppliedGiftsResponse(new AppliedGifts(new HashMap<>()));
        OutputView.printGiftDetails(appliedGiftsResponse);
        assertThat(output()).contains(
                "<증정 메뉴>",
                "없음"
        );
    }

    @DisplayName("혜택 내역 출력")
    @Test
    void printBenefitDetails() {
        AppliedDiscountsResponse appliedDiscountsResponse = getAppliedDiscountsResponse();
        AppliedGiftsResponse appliedGiftsResponse = getAppliedGiftsResponse();
        OutputView.printPromotionDetails(appliedDiscountsResponse, appliedGiftsResponse);
        assertThat(output()).contains(
                "<혜택 내역>",
                "크리스마스 디데이 할인: -1,200원",
                "평일 할인: -4,046원",
                "특별 할인: -1,000원",
                "증정 이벤트: -25,000원"
        );
    }

    @DisplayName("혜택 내역 없음")
    @Test
    void printNoBenefitDetails() {
        AppliedDiscountsResponse appliedDiscountsResponse =
                new AppliedDiscountsResponse(new AppliedDiscounts(new LinkedHashMap<>()));
        AppliedGiftsResponse appliedGiftsResponse =
                new AppliedGiftsResponse(new AppliedGifts(new HashMap<>()));
        OutputView.printPromotionDetails(appliedDiscountsResponse, appliedGiftsResponse);
        assertThat(output()).contains(
                "<혜택 내역>",
                "없음"
        );
    }

    private AppliedDiscountsResponse getAppliedDiscountsResponse() {
        Map<DiscountPromotion, Integer> discountDetails = new LinkedHashMap<>();
        discountDetails.put(DiscountPromotion.CHRISTMAS_D_DAY, 1_200);
        discountDetails.put(DiscountPromotion.WEEKDAY, 4_046);
        discountDetails.put(DiscountPromotion.SPECIAL_DAY, 1_000);
        return new AppliedDiscountsResponse(new AppliedDiscounts(discountDetails));
    }

    private AppliedGiftsResponse getAppliedGiftsResponse() {
        Map<Gift, Integer> gifts = new LinkedHashMap<>();
        gifts.put(Gift.CHAMPAGNE, Gift.CHAMPAGNE.calculatePrice());
        return new AppliedGiftsResponse(new AppliedGifts(gifts));
    }
}