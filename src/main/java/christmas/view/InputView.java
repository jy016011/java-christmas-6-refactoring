package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.constant.input.InputForm;
import christmas.view.constant.input.RequestInputMessage;

public class InputView {
    private static final String WHITE_SPACE = " ";

    private InputView() {
    }

    public static String requestVisitDayInput() {
        System.out.println(
                RequestInputMessage.REQUEST_DATE_TO_VISIT + WHITE_SPACE + InputForm.ONLY_NUMBER.getMessage()
        );
        return Console.readLine();
    }

    public static String requestOrder() {
        System.out.println(RequestInputMessage.REQUEST_ORDER + WHITE_SPACE + InputForm.ORDER_EXAMPLE.getMessage());
        return Console.readLine();
    }
}
