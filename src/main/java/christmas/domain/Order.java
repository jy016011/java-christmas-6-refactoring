package christmas.domain;

import static christmas.view.constant.output.ErrorMessage.ONLY_DRINK;

import christmas.domain.constraint.OrderConstraint;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.utils.ArgumentValidator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {
    private static final int MAX_COUNT_OF_MENU = OrderConstraint.MAX_COUNT_OF_TOTAL_MENU.getValue();
    private final Map<Menu, Integer> orderDetails;

    public Order(Map<Menu, Integer> orderDetailsInput) {
        validateHasOnlyDrinks(orderDetailsInput.keySet());
        validateTotalCounts(orderDetailsInput.values().stream().toList());
        orderDetails = orderDetailsInput;
    }

    public boolean isOriginTotalPriceNoLessThan(int standardAmount) {
        return calculateTotalOriginPrice() >= standardAmount;
    }

    public boolean hasAnyMenuIn(Class<? extends Menu> category) {
        return orderDetails.keySet().stream()
                .anyMatch(menu -> menu.isSameCategory(category));
    }

    public int calculateTotalOriginPrice() {
        return orderDetails.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * orderDetails.get(menu))
                .sum();
    }

    public int getCountOfMenuIn(Class<? extends Menu> category) {
        return orderDetails.keySet().stream()
                .filter(menu -> menu.isSameCategory(category))
                .mapToInt(orderDetails::get)
                .sum();
    }

    public Map<String, Integer> getOrderDetails() {
        return orderDetails.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Entry::getValue,
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    private void validateHasOnlyDrinks(Set<Menu> menus) {
        boolean hasOnlyDrinks = menus.stream().allMatch(menu -> menu.isSameCategory(Drink.class));
        ArgumentValidator.throwIllegalArgumentExceptionBy(hasOnlyDrinks, ONLY_DRINK.getMessage());
    }

    private void validateTotalCounts(List<Integer> menuCounts) {
        int totalCount = menuCounts.stream().mapToInt(count -> count).sum();
        ArgumentValidator.isNotGreaterThan(totalCount, MAX_COUNT_OF_MENU);
    }
}
