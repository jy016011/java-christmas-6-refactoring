package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.AppliedDiscounts;
import christmas.domain.AppliedGifts;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.badge.Badge;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.menu.Menu;
import christmas.domain.promotion.DiscountPromotion;
import christmas.domain.promotion.Gift;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BenefitServiceTest {
    private VisitingDate visitingDate;
    private Order order;

    private void setOrder() {
        Map<Menu, Integer> orderDetails = new HashMap<>();
        orderDetails.put(Main.T_BONE_STEAK, 1);
        orderDetails.put(Main.BARBECUE_RIBS, 1);
        orderDetails.put(Dessert.CHOCO_CAKE, 2);
        orderDetails.put(Drink.ZERO_COKE, 1);
        order = new Order(orderDetails);
    }

    private void setOrderWithOutGift() {
        Map<Menu, Integer> orderDetails = new HashMap<>();
        orderDetails.put(Main.T_BONE_STEAK, 1);
        order = new Order(orderDetails);
    }

    @DisplayName("12월 3일(평일, 별 있는 날), 티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개 주문시 할인혜택")
    @Test
    void getDiscountDetailsByExampleOnWeekday() {
        visitingDate = new VisitingDate(3);
        setOrder();
        AppliedDiscounts appliedDiscounts = BenefitService.getAppliedDiscounts(visitingDate, order);
        Map<String, Integer> discountContext = appliedDiscounts.getPromotionNameAndBenefit();
        assertThat(discountContext).containsOnlyKeys(
                DiscountPromotion.CHRISTMAS_D_DAY.getName(),
                DiscountPromotion.WEEKDAY.getName(),
                DiscountPromotion.SPECIAL_DAY.getName()
        );
        assertThat(discountContext.get(DiscountPromotion.CHRISTMAS_D_DAY.getName())).isEqualTo(1_200);
        assertThat(discountContext.get(DiscountPromotion.WEEKDAY.getName())).isEqualTo(4_046);
        assertThat(discountContext.get(DiscountPromotion.SPECIAL_DAY.getName())).isEqualTo(1_000);
        assertThat(appliedDiscounts.getTotalBenefitAmount()).isEqualTo(6_246);
    }

    @DisplayName("12월 3일(평일, 별 있는 날), 티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개 주문시 증정혜택")
    @Test
    void getGiftDetailsByExampleOnWeekday() {
        visitingDate = new VisitingDate(3);
        setOrder();
        AppliedGifts appliedGifts = BenefitService.getAppliedGifts(visitingDate, order);
        Map<String, Integer> giftDetails = appliedGifts.getGiftNameAndQuantity();
        assertThat(giftDetails).containsOnlyKeys(Gift.CHAMPAGNE.getMenu().getName());
        assertThat(appliedGifts.getTotalBenefitAmount()).isEqualTo(25_000);
    }

    @DisplayName("12월 2일(주말), 티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개 주문시 할인혜택")
    @Test
    void getDiscountDetailsByExampleOnWeekend() {
        visitingDate = new VisitingDate(2);
        setOrder();
        AppliedDiscounts appliedDiscounts = BenefitService.getAppliedDiscounts(visitingDate, order);
        Map<String, Integer> discountContext = appliedDiscounts.getPromotionNameAndBenefit();
        assertThat(discountContext).containsOnlyKeys(
                DiscountPromotion.CHRISTMAS_D_DAY.getName(),
                DiscountPromotion.WEEKEND.getName()
        );
        assertThat(discountContext.get(DiscountPromotion.CHRISTMAS_D_DAY.getName())).isEqualTo(1_100);
        assertThat(discountContext.get(DiscountPromotion.WEEKEND.getName())).isEqualTo(4_046);
        assertThat(appliedDiscounts.getTotalBenefitAmount()).isEqualTo(5_146);
    }

    @DisplayName("12월 3일(평일), 티본스테이크 1개 주문시 프로모션 혜택")
    @Test
    void getPromotionWithOutGift() {
        visitingDate = new VisitingDate(3);
        setOrderWithOutGift();
        AppliedDiscounts appliedDiscounts = BenefitService.getAppliedDiscounts(visitingDate, order);
        AppliedGifts appliedGifts = BenefitService.getAppliedGifts(visitingDate, order);
        Map<String, Integer> discountContext = appliedDiscounts.getPromotionNameAndBenefit();
        Map<String, Integer> giftDetails = appliedGifts.getGiftNameAndQuantity();
        assertThat(discountContext).containsOnlyKeys(
                DiscountPromotion.CHRISTMAS_D_DAY.getName(),
                DiscountPromotion.SPECIAL_DAY.getName()
        );
        assertThat(giftDetails).isEmpty();
        assertThat(discountContext.get(DiscountPromotion.CHRISTMAS_D_DAY.getName())).isEqualTo(1_200);
        assertThat(discountContext.get(DiscountPromotion.SPECIAL_DAY.getName())).isEqualTo(1_000);
        assertThat(appliedDiscounts.getTotalBenefitAmount()).isEqualTo(2_200);
    }

    @DisplayName("총 혜택 금액이 5천원 미만이면 배지는 없다")
    @Test
    void getNoneBadge() {
        int totalBenefitAmount = 4_999;
        assertThat(BenefitService.getBadgeBy(totalBenefitAmount)).isEqualTo(Badge.NONE);
    }

    @DisplayName("총 혜택 금액이 5천원이상 1만원 미만이면 배지는 별")
    @ValueSource(ints = {5_000, 9_999})
    @ParameterizedTest
    void getStarBadge(int totalBenefitAmount) {
        assertThat(BenefitService.getBadgeBy(totalBenefitAmount)).isEqualTo(Badge.STAR);
    }

    @DisplayName("총 혜택 금액이 1만원이상 2만원 미만이면 배지는 트리")
    @ValueSource(ints = {10_000, 19_999})
    @ParameterizedTest
    void getTreeBadge(int totalBenefitAmount) {
        assertThat(BenefitService.getBadgeBy(totalBenefitAmount)).isEqualTo(Badge.TREE);
    }

    @DisplayName("총 혜택 금액이 2만원 이상이면 배지는 산타")
    @ValueSource(ints = {20_000})
    @ParameterizedTest
    void getSantaBadge(int totalBenefitAmount) {
        assertThat(BenefitService.getBadgeBy(totalBenefitAmount)).isEqualTo(Badge.SANTA);
    }

    @DisplayName("총 혜택 금액이 0 미만이면 예외 발생")
    @ValueSource(ints = {-1, -20_000})
    @ParameterizedTest
    void getBadgeByInvalidAmount(int totalBenefitAmount) {
        assertThatThrownBy(() -> BenefitService.getBadgeBy(totalBenefitAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}