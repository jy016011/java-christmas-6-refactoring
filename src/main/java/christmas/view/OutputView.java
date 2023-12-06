package christmas.view;

import christmas.view.constant.output.OutputMessage;

public class OutputView {
    private OutputView() {
    }

    public static void printGreeting() {
        System.out.println(OutputMessage.GREETING);
    }
}
