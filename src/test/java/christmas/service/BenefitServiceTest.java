package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.dto.AppliedDiscounts;
import christmas.domain.dto.AppliedGifts;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.menu.Menu;
import christmas.domain.promotion.DiscountPromotion;
import christmas.domain.promotion.GiftPromotion;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitServiceTest {
    private VisitingDate visitingDate;
    private Order order;

    @BeforeEach
    void setOrder() {
        visitingDate = new VisitingDate(3);
        Map<Menu, Integer> orderDetails = new HashMap<>();
        orderDetails.put(Main.T_BONE_STEAK, 1);
        orderDetails.put(Main.BARBECUE_RIBS, 1);
        orderDetails.put(Dessert.CHOCO_CAKE, 2);
        orderDetails.put(Drink.ZERO_COKE, 1);
        order = new Order(orderDetails);
    }

    @DisplayName("12월 3일, 티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개 주문시 할인혜택")
    @Test
    void getDiscountDetailsByExample() {
        AppliedDiscounts appliedDiscounts = BenefitService.getApplicableDiscounts(visitingDate, order);
        Map<String, Integer> discountContext = appliedDiscounts.discountContext();
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

    @DisplayName("12월 3일, 티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개 주문시 증정혜택")
    @Test
    void getGiftDetailsByExample() {
        AppliedGifts appliedGifts = BenefitService.getApplicableGifts(visitingDate, order);
        Map<Menu, Integer> giftContext = appliedGifts.giftDetails();
        assertThat(giftContext).containsOnlyKeys(GiftPromotion.CHAMPAGNE_GIFT.getGift());
        assertThat(giftContext.get(GiftPromotion.CHAMPAGNE_GIFT.getGift())).isEqualTo(1);
        assertThat(appliedGifts.getTotalBenefitAmount()).isEqualTo(25_000);
    }
}