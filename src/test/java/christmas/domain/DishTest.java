package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.utils.StringParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DishTest {
    @DisplayName("메뉴판에 없는 메뉴 주문시 예외 발생")
    @ValueSource(strings = {"피자", "게살버거"})
    @ParameterizedTest
    void createDishByNotInMenu(String name) {
        assertThatThrownBy(() -> new Dish(name, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1개미만 혹은 20개 초과 주문시 예외 발생")
    @ValueSource(strings = {"0", "-1", "21"})
    @ParameterizedTest
    void createDishByInvalidCount(String userInput) {
        int count = StringParser.toInteger(userInput);
        assertThatThrownBy(() -> new Dish("타파스", count))
                .isInstanceOf(IllegalArgumentException.class);
    }
}