package christmas.view.constant.input;

import static christmas.domain.constraint.DateConstraint.PROMOTION_MONTH;

public interface RequestInputMessage {
    int MONTH = PROMOTION_MONTH.getValue();
    String REQUEST_DATE_TO_VISIT = String.format("%d월 중 식당 예상 방문 날짜는 언제인가요?", MONTH);
    String REQUEST_ORDER = "주문하실 메뉴와 개수를 알려 주세요.";
    String REQUEST_INPUT_AGAIN = "다시 입력해 주세요.";

    String getMessage();
}
