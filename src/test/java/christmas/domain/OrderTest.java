package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.menu.Menu;
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
}