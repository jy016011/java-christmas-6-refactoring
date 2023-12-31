package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.menu.Menu;
import christmas.domain.promotion.condition.order.OriginTotalPrice;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("음료만 주문할 수 없음")
    @Test
    void createOrderByOnlyDrinks() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Drink.CHAMPAGNE, 1);
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음식의 총합이 20개 초과할 수 없음")
    @Test
    void createOrderByOverMaxCountOfDishes() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Drink.CHAMPAGNE, 5);
        input.put(Main.BARBECUE_RIBS, 16);
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("티본스테이크 + 샴페인 가격의 합은 55,000원")
    @Test
    void createOrderByTBoneSteakAndChampagne() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Drink.CHAMPAGNE, 1);
        input.put(Main.T_BONE_STEAK, 1);
        Order order = new Order(input);
        assertThat(order.calculateTotalOriginPrice()).isEqualTo(80_000);
    }

    @DisplayName("만원 이상은 이벤트 대상")
    @Test
    void createApplicableOrder() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Dessert.ICE_CREAM, 2);
        Order order = new Order(input);
        assertThat(OriginTotalPrice.MIN_AMOUNT_FOR_APPLICABLE.isApplicablePrice(order)).isEqualTo(true);
    }

    @DisplayName("만원 미만은 이벤트 대상아님")
    @Test
    void createInApplicableOrder() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Appetizer.WHITE_MUSHROOM_SOUP, 1);
        input.put(Drink.ZERO_COKE, 1);
        Order order = new Order(input);
        assertThat(OriginTotalPrice.MIN_AMOUNT_FOR_APPLICABLE.isApplicablePrice(order)).isEqualTo(false);
    }

    @DisplayName("티본스테이크 2개, 아이스크림 2개(총 12만원)는 증정 이벤트 대상")
    @Test
    void createOrderToGetGift() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Main.T_BONE_STEAK, 2);
        input.put(Dessert.ICE_CREAM, 2);
        Order order = new Order(input);
        assertThat(OriginTotalPrice.LIMIT_AMOUNT_OF_GIFT_PROMOTION.isApplicablePrice(order)).isEqualTo(true);
    }

    @DisplayName("티본스테이크 2개, 양송이수프 1개, 제로콜라 1개는 (총 11만 9천원)는 증정 이벤트 대상아님")
    @Test
    void createOrderCanNotGetGift() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Main.T_BONE_STEAK, 2);
        input.put(Appetizer.WHITE_MUSHROOM_SOUP, 1);
        input.put(Drink.ZERO_COKE, 1);
        Order order = new Order(input);
        assertThat(OriginTotalPrice.LIMIT_AMOUNT_OF_GIFT_PROMOTION.isApplicablePrice(order)).isEqualTo(false);
    }

    @DisplayName("주문한 메뉴 중 주어진 카테고리에 해당하는 메뉴 개수 반환")
    @Test
    void getCountOfMenuByCategory() {
        Map<Menu, Integer> input = new LinkedHashMap<>();
        input.put(Main.T_BONE_STEAK, 2);
        input.put(Dessert.ICE_CREAM, 5);
        Order order = new Order(input);
        assertThat(order.getCountOfMenuIn(Main.class)).isEqualTo(2);
        assertThat(order.getCountOfMenuIn(Dessert.class)).isEqualTo(5);
    }
}