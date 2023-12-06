package christmas.service;

import christmas.domain.constraint.OrderConstraint;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;
import christmas.utils.ArgumentValidator;
import christmas.utils.StringParser;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParseService {
    private static final String MENU_SEPARATOR = ",";
    private static final String NAME_AND_COUNT_SEPARATOR = "-";
    private static final int DIFFERENCE_BETWEEN_SEPARATORS = 1;
    private static final int LIST_OF_NAME_AND_COUNT_SIZE = 2;
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private ParseService() {
    }

    public static int toNumber(String userInput) {
        ArgumentValidator.isNumber(userInput);
        return StringParser.toInteger(userInput);
    }

    public static Map<Menu, Integer> toOrderMap(String userInput) {
        validateFormat(userInput);
        Map<Menu, Integer> orderDetailsInput = new LinkedHashMap<>();
        recordOrder(orderDetailsInput, userInput);
        return orderDetailsInput;
    }

    private static void validateFormat(String userInput) {
        int menuSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringParser.toChar(MENU_SEPARATOR)).count();
        int nameAndCountSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringParser.toChar(NAME_AND_COUNT_SEPARATOR)).count();
        ArgumentValidator.isEqual(
                nameAndCountSeparatorCount - menuSeparatorCount,
                DIFFERENCE_BETWEEN_SEPARATORS
        );
    }

    private static void recordOrder(Map<Menu, Integer> orderDetailsInput, String userInput) {
        List<String> menuInputs = StringParser.toTrimmedStringList(userInput, MENU_SEPARATOR);
        menuInputs.forEach(menuInput -> recordMenuAndCount(orderDetailsInput, menuInput));
    }

    private static void recordMenuAndCount(Map<Menu, Integer> orderDetailsInput, String menuInput) {
        List<String> nameAndCount = StringParser.toTrimmedStringList(menuInput, NAME_AND_COUNT_SEPARATOR);
        validateIsUsedNameAndCountSeparator(nameAndCount);
        Menu menu = MenuBoard.getBy(nameAndCount.get(NAME_INDEX));
        int count = toNumber(nameAndCount.get(COUNT_INDEX));
        validateIsUnique(orderDetailsInput, menu);
        orderDetailsInput.put(menu, count);
    }

    private static void validateIsUsedNameAndCountSeparator(List<String> nameAndCount) {
        validateIsSeparated(nameAndCount.size());
        validateNameAndCount(nameAndCount);
    }

    private static void validateIsSeparated(int size) {
        ArgumentValidator.isEqual(size, LIST_OF_NAME_AND_COUNT_SIZE);
    }

    private static void validateNameAndCount(List<String> nameAndCount) {
        validateIsInMenu(nameAndCount.get(NAME_INDEX));
        validateIsInRange(nameAndCount.get(COUNT_INDEX));
    }

    private static void validateIsUnique(Map<Menu, Integer> orderDetailsInput, Menu menu) {
        ArgumentValidator.throwIllegalArgumentExceptionBy(
                orderDetailsInput.containsKey(menu),
                "중복된 메뉴 입력입니다."
        );
    }

    private static void validateIsInMenu(String menuName) {
        // will throw IllegalArgumentException when not in menu
        MenuBoard.getBy(menuName);
    }

    private static void validateIsInRange(String menuCount) {
        int count = toNumber(menuCount);
        ArgumentValidator.isNotLessThan(count, OrderConstraint.MIN_COUNT_OF_EACH_MENU.getValue());
        ArgumentValidator.isNotGreaterThan(count, OrderConstraint.MAX_COUNT_OF_TOTAL_MENU.getValue());
    }

}
