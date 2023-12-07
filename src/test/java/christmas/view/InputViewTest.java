package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    private PrintStream standardOut;
    private ByteArrayOutputStream captor;

    @BeforeEach
    public void init() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    public void reset() {
        System.setOut(standardOut);
        captor.reset();
        Console.close();
    }

    private String output() {
        return captor.toString();
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @DisplayName("날짜 입력 확인")
    @Test
    void requestVisitDay() {
        String userInput = "3";
        command(userInput);
        String systemReceived = InputView.requestVisitDayInput();
        assertThat(output()).contains(
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        );
        assertThat(systemReceived).isEqualTo(userInput);
    }

    @DisplayName("주문 입력 확인")
    @Test
    void requestOrder() {
        String userInput = "해산물파스타-2,레드와인-1,초코케이크-1";
        command(userInput);
        String systemReceived = InputView.requestOrder();
        assertThat(output()).contains(
                "주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
        );
        assertThat(systemReceived).isEqualTo(userInput);
    }
}