package christmas.service;

import christmas.utils.ArgumentValidator;
import christmas.utils.StringParser;

public class ParseService {
    private ParseService() {
    }

    public static int toNumberOfDay(String userInput) {
        ArgumentValidator.isNumber(userInput);
        return StringParser.toInteger(userInput);
    }
}
