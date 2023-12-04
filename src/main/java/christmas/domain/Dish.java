package christmas.domain;

import christmas.domain.constraint.OrderConstraint;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;
import christmas.utils.ArgumentValidator;

public class Dish {
    private static final int MIN_COUNT_OF_MENU = OrderConstraint.MIN_COUNT_OF_EACH_MENU.getValue();
    private static final int MAX_COUNT_OF_MENU = OrderConstraint.MAX_COUNT_OF_TOTAL_MENU.getValue();
    private final Menu menu;
    private final int count;

    public Dish(String name, int count) {
        menu = MenuBoard.getBy(name);
        validateCount(count);
        this.count = count;
    }

    private void validateCount(int count) {
        ArgumentValidator.isNotLessThan(count, MIN_COUNT_OF_MENU);
        ArgumentValidator.isNotGreaterThan(count, MAX_COUNT_OF_MENU);
    }
}
