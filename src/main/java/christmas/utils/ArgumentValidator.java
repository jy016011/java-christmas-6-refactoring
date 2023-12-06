package christmas.utils;

import java.util.List;

public class ArgumentValidator {
    private static final String ERROR_MESSAGE_HEADER = "[ERROR]";

    private ArgumentValidator() {
    }

    public static void isNumber(String input) {
        boolean isNotNumber = !input.chars().allMatch(Character::isDigit);
        if (isNotNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " 숫자만 입력해주세요.");
        }
    }

    public static void isNotLessThan(int checkingNumber, int standardNumber) {
        if (checkingNumber < standardNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + " 이상의 수를 입력하세요.");
        }
    }

    public static void isNotGreaterThan(int checkingNumber, int standardNumber) {
        if (checkingNumber > standardNumber) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + " 이하의 수를 입력하세요.");
        }
    }

    public static void isUnique(List<Object> objects) {
        boolean isDuplicated = objects.stream().distinct().count() != objects.size();
        if (isDuplicated) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " 중복이 있습니다.");
        }
    }

    public static void isEqual(int checkingNumber, int standardNumber) {
        boolean notMatchSize = checkingNumber != standardNumber;
        if (notMatchSize) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + standardNumber + "과(와) 다릅니다.");
        }
    }

    public static void throwIllegalArgumentExceptionBy(boolean condition, String message) {
        if (condition) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + message);
        }
    }

    private static void raiseIllegalArgumentException(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }
}
