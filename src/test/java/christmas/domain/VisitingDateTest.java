package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.utils.StringParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitingDateTest {
    @DisplayName("입력 숫자가 1보다 작거나 31초과 일경우 예외가 발생")
    @ValueSource(strings = {"0", "32"})
    @ParameterizedTest
    void createVisitingDateByOutOfRangeNumber(String userInput) {
        int day = StringParser.toInteger(userInput);
        assertThatThrownBy(() -> new VisitingDate(day)).isInstanceOf(IllegalArgumentException.class);
    }
}