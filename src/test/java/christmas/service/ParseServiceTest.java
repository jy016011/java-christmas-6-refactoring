package christmas.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParseServiceTest {
    @DisplayName("날짜 입력이 숫자가 아니면 예외가 발생할 것이다.")
    @Test
    void inputNotNumber() {
        String userInput = "12a";
        assertThatThrownBy(() -> ParseService.toNumberOfDay(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}