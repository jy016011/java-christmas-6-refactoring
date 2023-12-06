package christmas.service;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParseServiceTest {
    @DisplayName("날짜 입력이 숫자가 아니면 예외가 발생할 것이다.")
    @Test
    void inputNotNumber() {
        String userInput = "12a";
        assertThatThrownBy(() -> ParseService.toNumber(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    //쉼표와 하이픈의 개수는 양식대로 입력하면 항상 1만큼 차이가 난다.
    @DisplayName("쉼표(,)와 하이픈(-)을 갯수에 맞게 입력하지 않으면 예외가 발생할 것이다.")
    @ValueSource(strings = {"바비큐립1", "바비큐립-1,", "바비큐립: 1", "바비큐립-1 제로콜라-2"})
    @ParameterizedTest
    void inputWithoutValidCountOfSeparator(String input) {
        assertThatThrownBy(() -> ParseService.toOrderMap(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("없는 메뉴 입력시 예외가 발생할 것이다.")
    @ValueSource(strings = {"게살버거-1", "초코비-2"})
    @ParameterizedTest
    void inputNotInMenu(String input) {
        assertThatThrownBy(() -> ParseService.toOrderMap(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("있는 메뉴 입력시 예외 발생 안할 것이다.")
    @ValueSource(strings = {"타파스-1", "제로콜라-2"})
    @ParameterizedTest
    void inputInMenu(String input) {
        assertThatCode(() -> ParseService.toOrderMap(input)).doesNotThrowAnyException();
    }

    @DisplayName("개수가 1미만이거나 20초과시 예외가 발생할 것이다.")
    @ValueSource(strings = {"타파스-0", "제로콜라-21"})
    @ParameterizedTest
    void inputCountIsOutOfRange(String input) {
        assertThatThrownBy(() -> ParseService.toOrderMap(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴 입력이 있을 시 예외가 발생할 것이다.")
    @ValueSource(strings = {"타파스-1,타파스-2", "타파스-1,바비큐립-1,바비큐립-1,제로콜라-1"})
    @ParameterizedTest
    void inputDuplicatedMenu(String input) {
        assertThatThrownBy(() -> ParseService.toOrderMap(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이외 쉼표와 하이픈의 개수를 통해 형식을 검증하는 기능의 허점을 이용한 입력시 예외 발생 검증")
    @ValueSource(strings = {"-바비큐립-1,초코케이크-2,", "초코케이크,--"})
    @ParameterizedTest
    void inputInvalidFormatNotCaughtByValidationOfFormat(String input) {
        assertThatThrownBy(() -> ParseService.toOrderMap(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}