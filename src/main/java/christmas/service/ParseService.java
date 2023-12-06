package christmas.service;

import static christmas.domain.constraint.OrderConstraint.MAX_COUNT_OF_TOTAL_MENU;
import static christmas.domain.constraint.OrderConstraint.MIN_COUNT_OF_EACH_MENU;
import static christmas.service.constraint.DataStructureConstraint.COUNT_INDEX;
import static christmas.service.constraint.DataStructureConstraint.DIFFERENCE_BETWEEN_SEPARATORS;
import static christmas.service.constraint.DataStructureConstraint.LIST_OF_NAME_AND_COUNT_SIZE;
import static christmas.service.constraint.DataStructureConstraint.NAME_INDEX;
import static christmas.service.constraint.OrderInputConstraint.MENU_SEPARATOR;
import static christmas.service.constraint.OrderInputConstraint.NAME_AND_COUNT_SEPARATOR;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;
import christmas.utils.ArgumentValidator;
import christmas.utils.StringParser;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParseService {
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
                .filter(c -> c == StringParser.toChar(MENU_SEPARATOR.getValue())).count();
        int nameAndCountSeparatorCount = (int) userInput.chars()
                .filter(c -> c == StringParser.toChar(NAME_AND_COUNT_SEPARATOR.getValue())).count();
        ArgumentValidator.isEqual(
                nameAndCountSeparatorCount - menuSeparatorCount,
                DIFFERENCE_BETWEEN_SEPARATORS.getValue()
        );
    }

    private static void recordOrder(Map<Menu, Integer> orderDetailsInput, String userInput) {
        List<String> menuInputs = StringParser.toTrimmedStringList(userInput, MENU_SEPARATOR.getValue());
        menuInputs.forEach(menuInput -> recordMenuAndCount(orderDetailsInput, menuInput));
    }

    private static void recordMenuAndCount(Map<Menu, Integer> orderDetailsInput, String menuInput) {
        List<String> nameAndCount = StringParser.toTrimmedStringList(menuInput, NAME_AND_COUNT_SEPARATOR.getValue());
        validateIsUsedNameAndCountSeparator(nameAndCount);
        Menu menu = MenuBoard.getBy(nameAndCount.get(NAME_INDEX.getValue()));
        int count = toNumber(nameAndCount.get(COUNT_INDEX.getValue()));
        validateIsUnique(orderDetailsInput, menu);
        orderDetailsInput.put(menu, count);
    }

    private static void validateIsUsedNameAndCountSeparator(List<String> nameAndCount) {
        validateIsSeparated(nameAndCount.size());
        validateNameAndCount(nameAndCount);
    }

    private static void validateIsSeparated(int size) {
        ArgumentValidator.isEqual(size, LIST_OF_NAME_AND_COUNT_SIZE.getValue());
    }

    private static void validateNameAndCount(List<String> nameAndCount) {
        validateIsInMenu(nameAndCount.get(NAME_INDEX.getValue()));
        validateIsInRange(nameAndCount.get(COUNT_INDEX.getValue()));
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
        ArgumentValidator.isNotLessThan(count, MIN_COUNT_OF_EACH_MENU.getValue());
        ArgumentValidator.isNotGreaterThan(count, MAX_COUNT_OF_TOTAL_MENU.getValue());
    }

}
