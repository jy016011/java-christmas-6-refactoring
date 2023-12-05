package christmas.domain;

import christmas.domain.constraint.OrderConstraint;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.utils.ArgumentValidator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order {
    private static final int MAX_COUNT_OF_MENU = OrderConstraint.MAX_COUNT_OF_TOTAL_MENU.getValue();
//    private final Map<Menu, Integer> orderDetails;

    Order(Map<Menu, Integer> orderDetailsInput) {
        validateHasOnlyDrinks(orderDetailsInput.keySet());
        validateTotalCounts(orderDetailsInput.values().stream().toList());
//        orderDetails = setOrderDetails(menuNames, menuCounts);
    }

//    private Map<Menu, Integer> setOrderDetails(List<String> menuNames, List<Integer> menuCounts) {
//
//    }

    private void validateHasOnlyDrinks(Set<Menu> menus) {
        boolean hasOnlyDrinks = menus.stream().allMatch(menu -> menu.isSameCategory(Drink.class));
        ArgumentValidator.throwIllegalArgumentExceptionBy(hasOnlyDrinks, "음료만 주문할 수 없습니다.");
    }

    private void validateTotalCounts(List<Integer> menuCounts) {
        int totalCount = menuCounts.stream().mapToInt(count -> count).sum();
        ArgumentValidator.isNotGreaterThan(totalCount, MAX_COUNT_OF_MENU);
    }
}
