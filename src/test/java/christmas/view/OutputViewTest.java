package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
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
    }

    private String output() {
        return captor.toString();
    }

    @DisplayName("인사 문구 출력")
    @Test
    void printGreeting() {
        OutputView.printGreeting();
        assertThat(output()).contains("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
}