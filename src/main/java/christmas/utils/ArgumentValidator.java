package christmas.utils;

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

    public static void isDivisor(int dividend, int divisor) {
        boolean canNotDivide = !(dividend % divisor == 0);
        if (canNotDivide) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + divisor + "단위 이상으로 입력하세요.");
        }
    }

    public static void isEqual(int size, int limit) {
        boolean notMatchSize = size != limit;
        if (notMatchSize) {
            raiseIllegalArgumentException(ERROR_MESSAGE_HEADER + " " + limit + "개만큼 입력하세요.");
        }
    }

    private static void raiseIllegalArgumentException(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }
}
