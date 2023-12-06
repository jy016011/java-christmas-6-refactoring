package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.badge.Badge;
import christmas.domain.dto.AppliedDiscounts;
import christmas.domain.dto.AppliedGifts;
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

    @DisplayName("12월 3일(평일, 별 있는 날), 티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개 주문시 할인혜택")
    @Test
    void getDiscountDetailsByExampleOnWeekday() {
        visitingDate = new VisitingDate(3);
        setOrder();
        AppliedDiscounts appliedDiscounts = BenefitService.getApplicableDiscounts(visitingDate, order);
        Map<String, Integer> discountContext = appliedDiscounts.details();
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
        AppliedGifts appliedGifts = BenefitService.getApplicableGifts(visitingDate, order);
        Map<Gift, Integer> giftDetails = appliedGifts.giftDetails();
        assertThat(giftDetails).containsOnlyKeys(Gift.CHAMPAGNE);
        assertThat(appliedGifts.getTotalBenefitAmount()).isEqualTo(25_000);
    }

    @DisplayName("12월 2일(주말), 티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개 주문시 할인혜택")
    @Test
    void getDiscountDetailsByExampleOnWeekend() {
        visitingDate = new VisitingDate(2);
        setOrder();
        AppliedDiscounts appliedDiscounts = BenefitService.getApplicableDiscounts(visitingDate, order);
        Map<String, Integer> discountContext = appliedDiscounts.details();
        assertThat(discountContext).containsOnlyKeys(
                DiscountPromotion.CHRISTMAS_D_DAY.getName(),
                DiscountPromotion.WEEKEND.getName()
        );
        assertThat(discountContext.get(DiscountPromotion.CHRISTMAS_D_DAY.getName())).isEqualTo(1_100);
        assertThat(discountContext.get(DiscountPromotion.WEEKEND.getName())).isEqualTo(4_046);
        assertThat(appliedDiscounts.getTotalBenefitAmount()).isEqualTo(5_146);
    }

    @DisplayName("총 혜택 금액이 5천원 미만일이 배지는 없다")
    @Test
    void getNoneBadge() {
        int totalBenefitAmount = 4_999;
        assertThat(BenefitService.getBadgeBy(totalBenefitAmount)).isEqualTo(Badge.NONE);
    }

    @DisplayName("총 혜택 금액이 5천원이상 1만원 미만일시 배지는 별")
    @ValueSource(ints = {5_000, 9_999})
    @ParameterizedTest
    void getStarBadge(int totalBenefitAmount) {
        assertThat(BenefitService.getBadgeBy(totalBenefitAmount)).isEqualTo(Badge.STAR);
    }

    @DisplayName("총 혜택 금액이 1만원이상 2만원 미만일시 배지는 트리")
    @ValueSource(ints = {10_000, 19_999})
    @ParameterizedTest
    void getTreeBadge(int totalBenefitAmount) {
        assertThat(BenefitService.getBadgeBy(totalBenefitAmount)).isEqualTo(Badge.TREE);
    }
}